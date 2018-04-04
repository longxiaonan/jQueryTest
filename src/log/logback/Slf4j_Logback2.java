package log.logback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Slf4j_Logback2 {
	final Logger logger = LoggerFactory.getLogger(Slf4j_Logback2.class);
	Integer t;
	Integer oldT;
	public void setTemperature(Integer temperature) {
		oldT = t;
		t = temperature;
		for(int i = 0; i < 2; i++){
			logger.error(" Temperature set to {}. Old temperature was {}. ", t, oldT);
			logger.debug("11111111111{}2222{}", "aaaa","bbbb");
			if (temperature.intValue() > 50) {
				logger.info(" Temperature has risen above 50 degrees. ");
			}
		}
	}
	public static void main(String[] args) {
		Slf4j_Logback2 wombat = new Slf4j_Logback2();
		wombat.setTemperature(1);
		wombat.setTemperature(55);
	}
}