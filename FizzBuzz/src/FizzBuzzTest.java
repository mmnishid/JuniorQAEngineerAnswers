import org.junit.Assert;

public class FizzBuzzTest {
	 
	public static boolean fizzTest(int num) {
	    Assert.assertTrue("Number is divisible by 3", isDivisibleBy(3, num));
	    Assert.assertTrue("Output must contain 'Fizz'", fizzbuzz(num).contains("Fizz"));
	    return true;
	}
	 
	public static boolean buzzTest(int num) {
		Assert.assertTrue("Number is divisible by 5", isDivisibleBy(5, num));
		Assert.assertTrue("Output must contain 'Buzz'", fizzbuzz(num).contains("Buzz"));
		return true;
	}
	 
	public static boolean numberTest(int num) {
		String fizzbuzz = fizzbuzz(num);
		Assert.assertTrue("Output is a number", isANumber(fizzbuzz));
	    Assert.assertTrue("Output must be string representation of input number",
	    		num == Integer.valueOf(fizzbuzz));
	    return true;
	    
	}
	 
	private static boolean isDivisibleBy(int divisor, int number) {
		return (number % divisor) == 0;
	}
		
	private static boolean isANumber(String str) {
		return str.chars().allMatch(Character::isDigit);
	} 
	private static boolean isValidFizzBuzz(int num) {
		
		String result = fizzbuzz(num);
		if( fizzbuzz(num).equals("FizzBuzz") ) {
			return fizzTest(num) && buzzTest(num);
		} else if( fizzbuzz(num).equals("Fizz") ) {
			return fizzTest(num);
		} else if( fizzbuzz(num).equals("Buzz") ) {
			return buzzTest(num);
		} else if( isANumber( fizzbuzz(num) ) ) {
			return numberTest(num);
		} else {
			return false;
		}
	}
	

	
	private static String fizzbuzz( int num ) {
		if ( num % 3 == 0 && num % 5 == 0 ) {
			return "FizzBuzz";
		} else if ( num % 3 == 0) {
			return "Fizz";
		} else if( num % 5 == 0) {
			return "Buzz";
		} else {
			return Integer.toString(num);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i = 1; i <= 100; i++) {
			System.out.println("FizzBuzz(" + i + ") = " + fizzbuzz(i));
			Assert.assertTrue( "Output must be valid FizzBuzz", isValidFizzBuzz(i) );
		}
	}

}
