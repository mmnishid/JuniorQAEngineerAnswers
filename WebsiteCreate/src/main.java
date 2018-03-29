
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        WebsiteCreate UnitTest1 = new  WebsiteCreate( 
              "Test1", "Example1", false, false, false, false, false, 1, 0, "example", "", 
              0, "WordpressUnitTest1", "UnitTest1", "UnitTest1@gmail.com" );
        
        WebsiteCreate UnitTest2 = new  WebsiteCreate( 
                "Test2", "Example2", false, false, false, true, false, 1, 2, "", "JuniorQAExample.com", 
                2, "WordpressUnitTest1", "UnitTest1", "UnitTest1@gmail.com" );
        
        try {
			UnitTest1.setUp();
			UnitTest2.setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
