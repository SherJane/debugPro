package jwc.debug_pro.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelTest {

	public static void main(String[] args) {
		ExpressionParser parser = new SpelExpressionParser();
		ABean ab = new ABean(777, "测试bean");
        EvaluationContext ctx = new StandardEvaluationContext();
        ctx.setVariable("ab", ab);
        int id = (Integer) parser.parseExpression("#ab.getId() + 1900").getValue(ctx);
        System.out.println(id);

	}
	
	/**
	 * https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions
	 */
	private void testOp() {
		ExpressionParser parser = new SpelExpressionParser();
		System.out.println(parser.parseExpression("5>2"));
		System.out.println(parser.parseExpression("2 between {1,9}"));
	}

	private static class ABean {
		private int id;
		private String name;
		private int[] numbers = {1,2,3,4,5,6};
		
		ABean(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		
		int getId() {
			return id;
		}
		
		String getName() {
			return name;
		}
		
		int[] getNumbers() {
			return numbers;
		}
	}
}
