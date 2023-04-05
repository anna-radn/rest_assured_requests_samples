
import org.apache.juneau.json.JsonParser;
import org.apache.juneau.json.JsonSerializer;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.testng.annotations.Test;

public class PostApiUsingPojo {

	@Test
	public void serializePojo() throws SerializeException, ParseException {
		// serialize:convert POJO to json
		JsonSerializer js = JsonSerializer.DEFAULT_READABLE;
		EmployeePojo ep = new EmployeePojo("Tom Smith", "manager", new String[] { "Java", "C" }, "XYZ",
				"tomsmith@abc.com");
		String json = js.serialize(ep);
		System.out.println(json);
		
		//deserialization:convert json to POJO, must have POJO ready
		JsonParser jp=JsonParser.DEFAULT;
		String jsonValue="{\r\n"
				+ "	\"details\": {\r\n"
				+ "		\"companyName\": \"XYZ\",\r\n"
				+ "		\"emailId\": \"tomsmith@abc.com\"\r\n"
				+ "	},\r\n"
				+ "	\"job\": \"manager\",\r\n"
				+ "	\"name\": \"Tom Smith\",\r\n"
				+ "	\"skills\": [\r\n"
				+ "		\"Java\",\r\n"
				+ "		\"C\"\r\n"
				+ "	]\r\n"
				+ "}";      //this json is taken from EmployeePojo class above
		EmployeePojo emp=jp.parse(jsonValue, EmployeePojo.class);
		//fetching details from json:
		System.out.println(emp.getDetails());
		System.out.println(emp.getJob());
		System.out.println(emp.getName());
		System.out.println(emp.getSkills());
	}

}
