package TTCParser;

public enum Station {
	
	// YUS
	DOWNSVIEW("Downsview"),
	WILSON("Wilson"),
	LAWERENCEWEST("Lawerence West"),
	GLENCAIRN("Glencairn"),
	EGLINGTONWEST("Eglinton West"),
	STCLAIRWEST("St. Clair West"),
	DUPONT("Dupont"),
	SPADINA("Spadina"),
	STGEORGE("St. George"),
	MUSEUM("Museum"),
	QUEENSPARK("Queen's Park"),
	STPATCIK("St. Patrick"),
	OSGOODE("Osgoode"),
	STANDREW("St. Andrew"),
	UNION("Union"),
	KING("King"),
	QUEEN("Queen"),
	DUNDAS("Dundas"),
	COLLEGE("College"),
	WELLESLEY("Wellesley"),
	BLOORYONGE("Bloor-Yonge"),
	ROSEDALE("Rosedale"),
	SUMMERHILL("Summerhill"),
	STCLAIR("St. Clair"),
	DAVISVILLE("Davisville"),
	EGLINGTON("Eglinton"),
	LAWRENCE("Lawrence"),
	YORKMILLS("York Mills"),
	SHEPPARDYONGE("Sheppard-Yonge"),
	NORTHYORKCENTRE("North York Centre"),
	FINCH("Finch"),
	
	// Bloor
	KIPLING("Kipling"),
	ISLINGTON("Islington"),
	ROYALYORK("Royal York"),
	OLDMILL("Old Mill"),
	JANE("Jane"),
	RUNNYMEDE("Runnymede"),
	HIGHPARK("High Park"),
	KEELE("Keele"),
	DUNDASWEST("Dundas West"),
	LANDSDOWNE("Landsdowne"),
	DUFFERIN("Dufferin"),
	OSSINGTON("Ossington"),
	CHRISTIE("Chrisie"),
	BATHURST("Bathurst"),
	BAY("Bay"),
	SHERBOURNE("Sherbournce"),
	CASTLEFRANK("Castle Frank"),
	BROADVIEW("Broadview"),
	CHESTER("Chester"),
	PAPE("Pape"),
	DONLANDS("Donlands"),
	GREENWOOD("Greenwood"),
	COXWELL("Coxwell"),
	WOODBINE("Woodbine"),
	MAINSTREET("Main Street"),
	VICTORIAPARK("Victoria Park"),
	WARDEN("Warden"),
	KENNEDY("Kennedy"),
	
	// Sheppard
	BAYVIEW("Bayview"),
	BESSARION("Bessarion"),
	LESLIE("Leslie"),
	DONMILLS("Don Mills"),
	
	// Scarborough RT
	LAWRENCEEAST("Lawrence East"),
	ELLSMERE("Ellsmere"),
	MIDLAND("Midland"),
	SCARBOROUGHCENTRE("Scarborough Centre"),
	MCCOWAN("McCowan");
	
	
	private final String _name;
	Station(String name) {
		_name = name;
	}
	public String getName() { return _name; }
}
