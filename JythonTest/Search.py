import collections
import json
import requests
import requests.packages.urllib3
import sys


#********************************************************************************************
# Utility Methods
#********************************************************************************************

def http_get(url):
    print "HTTP GET: " + url
    response = requests.get(url)
    print "HTTP GET: " + url + ": " + str(response.status_code)
    return response.json()

def prettyfy(data):
    return json.dumps(data, indent=2)

def get_products(url, query):
    result = http_get(url + "?query=" + query)
    return result["data"]["products"]

def get_product_data(url, product_id):
    result = http_get(url + product_id)
    return result

def get_pet_list(product_data):
    categories = product_data["data"]["categories"]
    categories = filter(lambda x: x["id"] == "Pet", categories)[0]["tags"]
    return categories

def send_to_cyfe(url, payload):
    print "sending data to cyfe: " + url
    response = requests.post(url, data=json.dumps(payload))
    print "sending data to cyfe: " + url + ": " + str(response.status_code)

def create_cyfe_payload(pet_counts, query):
    data_dict = collections.OrderedDict()
    dupe_dict = dict()
    
    data_dict["query"] = query
    
    for pet in counts.keys():
        data_dict[pet] = str(pet_counts[pet])
        dupe_dict[pet] = "replace"
    
    data_list = list()
    data_list.append(data_dict)
    
    retval = dict()
    retval["data"] = data_list
    retval["onduplicate"] = dupe_dict
    
    return retval


#********************************************************************************************
# Program
#********************************************************************************************

search_url = "http://49.156.19.60:8080/beethoven/products/"
cyfe_url = "https://app.cyfe.com/api/push/57e4bb98371192734567702566944"

print "------------- START -------------"

requests.packages.urllib3.disable_warnings()

query = " ".join(sys.argv[1:])
print "query: " + query

# get a list of all products
products = get_products(search_url, query)

# dictionary to hold pet counts
counts = dict()

for product in products:
    
    # get product data
    product_data = get_product_data(search_url, product)
    
    # get list of pets
    pet_list = get_pet_list(product_data)
    
    for pet in pet_list:
        if not pet in counts.keys():
            counts[pet] = 0
        counts[pet] += 1

payload = create_cyfe_payload(counts, query)

print prettyfy(payload)

send_to_cyfe(cyfe_url, payload)

print "------------- END -------------"
