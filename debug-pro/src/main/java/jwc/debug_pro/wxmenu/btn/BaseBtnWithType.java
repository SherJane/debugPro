package jwc.debug_pro.wxmenu.btn;

public class BaseBtnWithType extends BaseBtn {
	protected String type;

	protected BaseBtnWithType(String name, String type) {
		super(name);
		this.type = type;
	}
}
