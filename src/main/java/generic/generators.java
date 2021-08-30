package generic;

import com.github.javafaker.Faker;

public class generators {
	
	static Faker faker=new Faker();
	
	public static String generate_email()
	{
		String email = faker.name().firstName() + "." + faker.name().lastName() +"@fakeEmail.com";
		return email;
	}
	
	public static String generate_name()
	{
		return faker.name().firstName();
	}
	
//	public static String generate_password()
//	{
//		
//	}
	

}
