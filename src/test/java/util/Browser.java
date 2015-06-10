package util;

/*
 * Bean representing a browser. It contains name, version and platform fields.
 */
public class Browser {

	private String name;
	private String version;
	private String platform;
	private String maxInstances;
	private String maxSession;
	private String nodeTimeout;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getMaxInstances() {
		return maxInstances;
	}

	public void setMaxInstances(String maxInstances) {
		this.maxInstances = maxInstances;
	}

	public String getMaxSession() {
		return maxSession;
	}

	public void setMaxSession(String maxSession) {
		this.maxSession = maxSession;
	}
	
	public String getNodeTimeout() {
		return nodeTimeout;
	}

	public void setNodeTimeout(String nodeTimeout) {
		this.nodeTimeout = nodeTimeout;
	}
	
	public String getVersion() {
		return version;
	}	

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

}