import java.io.Serializable;
import java.util.Date;

public class Ot implements Serializable {
	private static final long serialVersionUID = 1L;
	private int numb;
	private String task;
	private Date datum;
	private int prior;
	private String name;

	public Ot(int numb, String task, Date datum, int prior, String name) {
		super();
		this.numb = numb;
		this.task = task;
		this.datum = datum;
		this.prior = prior;
		this.name = name;
	}

	public int getNumb() {
		return numb;
	}

	public String getTask() {
		return task;
	}

	public Date getDatum() {
		return datum;
	}

	public int getPrior() {
		return prior;
	}

	public String getName() {
		return name;
	}
}
