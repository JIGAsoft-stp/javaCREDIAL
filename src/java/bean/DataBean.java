
package bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Helio
 */
@ManagedBean
public class DataBean 
{
    private Date data;
    private Date maxDate;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private Date minDate;
    public DataBean()
    {
        
    }
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public void enviar()
    {
        FacesContext context = FacesContext.getCurrentInstance();
       
        RequestContext.getCurrentInstance().execute("PF('modal').show()");
    }
    
    public Date getMaxDate() {
        return maxDate = (new Date());
    }
    
    public Date getMaior18() throws ParseException {
       String[] dd = sdf.format(new Date()).split("-");
       return maxDate =(sdf.parse("01-01-"+(Integer.valueOf(dd[2])-18)));
    }
    
    public Date getMaior16() throws ParseException {
       String[] dd = sdf.format(new Date()).split("-");
       return maxDate =(sdf.parse("01-01-"+(Integer.valueOf(dd[2])-16)));
    }

    public Date getMinDate() throws ParseException {
        String[] dd = sdf.format(new Date()).split("-");
        return minDate =(sdf.parse("01-01-"+(Integer.valueOf(dd[2])-190)));
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }
    
    
}
