package com.existingeevee.chickeneer.genetics.serializers;

import java.util.HashMap;
import java.util.Map;

import com.existingeevee.chickeneer.data.Color;
import com.existingeevee.chickeneer.genetics.AlleleSerializer;
import com.existingeevee.chickeneer.genetics.allele.ColorAllele;
import com.existingeevee.chickeneer.utils.Utils;

public class ColorAlleleSerializer extends AlleleSerializer<ColorAllele>{

	public ColorAlleleSerializer() {
		super(ColorAllele.class);
	}

	@Override
	public ColorAllele serializeFromJson(String jsonData) {
		Map<String,String> jsonMap = Utils.readJson(jsonData);
		return new ColorAllele(
				jsonMap.get("dominant").equalsIgnoreCase("true"),
				new Color(
						Integer.parseInt(jsonMap.get("red")),
						Integer.parseInt(jsonMap.get("green")),
						Integer.parseInt(jsonMap.get("blue"))),
				jsonMap.get("blend").equalsIgnoreCase("true"),
				Float.parseFloat(jsonMap.get("blendChance")));
	}

	@Override
	public String deserializeToJson(ColorAllele allele) {
		Map<String,String> jsonMap = new HashMap<String,String>();
		jsonMap.put("serializer", this.getClass().getName());

		jsonMap.put("red", Integer.toString(allele.getValue().getR()));
		jsonMap.put("green", Integer.toString(allele.getValue().getR()));
		jsonMap.put("blue", Integer.toString(allele.getValue().getR()));

		jsonMap.put("dominant", allele.isDominent() ? "true" : "false");
		jsonMap.put("blend", allele.isBlendable() ? "true" : "false");
		jsonMap.put("blendChance", Float.toString(allele.getBlendChance()));

		return Utils.mapToJson(jsonMap);
	}

}
