package iscte.iul.pt.defectDetention;

public class Rule {

	
	private String metric;
	private String symbol;
	private double threshold;
	private String ruleDescription;
	private String defects;
	
	public Rule(String metric, String symbol, double threshold, String defects) {
		
		this.metric = metric;
		this.symbol = symbol;
		this.threshold = threshold;
		this.ruleDescription = new String();
		this.defects = defects;
	}

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public String getRuleDescription() {
		return ruleDescription;
	}

	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}

	public String getDefects() {
		return defects;
	}

	public void setDefects(String defects) {
		this.defects = defects;
	}
	
	
	
}
