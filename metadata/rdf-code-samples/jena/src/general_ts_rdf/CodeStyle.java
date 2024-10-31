package general_ts_rdf;

/*
** Types of programming, coding, style constants.
*/
public enum CodeStyle {

	// constructors
	FUNCTIONAL (1),
	TRY_RESOURCE (2);
	
	private final int codeStyle;
	
	// constructor
	private CodeStyle(int codeStyle) {
		this.codeStyle = codeStyle;
	}
	
	public int getValue() {
		return(codeStyle);
	}
	
}