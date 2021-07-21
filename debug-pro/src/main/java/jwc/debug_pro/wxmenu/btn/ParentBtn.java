package jwc.debug_pro.wxmenu.btn;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ParentBtn<T> extends BaseBtn {
	@Setter
	private List<T> sub_button;
	
	public ParentBtn(String name) {
		super(name);
	}
}
