package il.ac.hit.weather.app;

import il.ac.hit.weather.gui.FiveDaysWeatherGui;

public class WeatherApplication
{
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
				@Override
				public void run() {
					
						FiveDaysWeatherGui gui = new FiveDaysWeatherGui();
						gui.show();		
				}
			});
	}
}
