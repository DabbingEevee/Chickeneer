package com.existingeevee.chickeneer.genetics;

import java.util.HashMap;
import java.util.Map;

public abstract class AlleleSerializer<A extends Allele<?>> {

	private Class<A> alleleClass;

	public static Map<String,AlleleSerializer<?>> serializers = new HashMap<String,AlleleSerializer<?>>();

	public AlleleSerializer(Class<A> alleleClass) {
		this.alleleClass = alleleClass;
		serializers.put(alleleClass.getName(), this);
	}

	public abstract A serializeFromJson(String jsonData);

	public abstract String deserializeToJson(A allele);

	public Class<A> getAlleleClass() {
		return alleleClass;
	}

	@SuppressWarnings("unchecked")
	public String deserializeToJsonH(Allele<?> alleleA) {
		return deserializeToJson((A) alleleA);
	}


}
