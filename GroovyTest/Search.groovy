@Grab("org.codehaus.groovy.modules.http-builder:http-builder:0.7")
import groovyx.net.http.RESTClient
@Grab(group='org.codehaus.gpars', module='gpars', version='1.0.0')
import groovyx.gpars.*
import groovy.json.*
import groovy.transform.Field

String searchUrl = "http://49.156.19.60:8080/beethoven/"
String cyfeUrl = "https://app.cyfe.com/api/push/57d24a1b414a59374644192521488"

println "------------- START -------------"

// join input arguments into a single string to use as query
String query = String.join(" ", args)
println query

// map to hold pet counts
Map counts = Collections.synchronizedMap([:])

// get a list of products
def products = getProducts(searchUrl, query)

GParsPool.withPool {
    products.eachParallel
    {
        // get product details
        def resData = getProductData(searchUrl, it)
        
        // get list of pets
        def pets = getPetList(resData)
        pets.each
        {       
            // increase pet count
            Integer count = counts[it]
            if(count == null) count = 0
            counts[it] = ++count       
        }
    }
}
String payload = createCyfePayload(counts, query)

println payload
//sendToCyfe(cyfeUrl, payload)

println "------------- END -------------"


//********************************************************************************************
// Utility Methods
//********************************************************************************************

List<String> getProducts(String url, String query)
{
    println "querying search engine: " + query
    RESTClient client = new RESTClient(url)
    def response = client.get(path: "products", query: ["query": query])
    def responseData = response.getData()
    println "querying search engine: " + query + ": " + responseData.status
    return responseData.data.products
}

Map getProductData(String url, String productId)
{
    println "fetching product details: " + productId
    RESTClient client = new RESTClient(url)
    def response = client.get(path: "products" + "/" + productId)
    def responseData = response.getData()
    println "fetching product details: " + productId + ": " + responseData.status
    return responseData
}

List<String> getPetList(Map productData)
{
    List<String> list = []
    def values = productData.data.categories.stream()
                                        .filter {x -> x.id == "Pet"}
                                        .map {x -> x.tags}
                                        .collect()
                                        .forEach(list.&addAll)
    return list
}

String createCyfePayload(Map<String, Integer> petCounts, String query)
{
    def dataMap = [:]
    dataMap["query"] = query
    petCounts.keySet().each
    {
        dataMap[it] = "" + petCounts[it]
    }

    def dupeValues = [:]
    petCounts.keySet().each
    {
        dupeValues[it] = "replace"
    }

    //wrap pet list into an array
    def dataList = [dataMap]

    def payload = JsonOutput.toJson({
        data dataList
        onduplicate dupeValues
    })

    return payload
}

void sendToCyfe(String url, String payload)
{
    print "sending data to cyfe ............."
    RESTClient client = new RESTClient(url)
    def cyfeResponse = client.post(body: payload, requestContentType: "application/json")
    println cyfeResponse.getData().status
}