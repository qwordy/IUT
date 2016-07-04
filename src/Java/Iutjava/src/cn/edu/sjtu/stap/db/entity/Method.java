package cn.edu.sjtu.stap.db.entity;

import cn.edu.sjtu.stap.Config;
import cn.edu.sjtu.stap.db.IEntity;

public class Method {

	String packageName, type, signature;
	
	public Method (String packageName, String type, String signature) {
		this.packageName = packageName;
		this.type = type;
		this.signature = signature;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append(packageName).append(Config.instSeperator)
			.append(type).append(Config.instSeperator)
			.append(signature);
		/*
		sb.append("package: ").append(packageName).append('\n')
			.append("type: ").append(type).append('\n')
			.append("signature: ").append(signature).append('\n');
			*/
		return sb.toString();
	}

}
