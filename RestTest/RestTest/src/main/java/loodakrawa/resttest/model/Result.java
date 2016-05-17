package loodakrawa.resttest.model;

import java.util.List;

import lombok.Data;

@Data
public class Result {
	private final List<Item> matches;
}
