package il.ac.hit.weather.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import il.ac.hit.weather.factory.IWeatherDataService;
import il.ac.hit.weather.factory.WeatherDataServiceFactory;
import il.ac.hit.weather.model.Location;
import il.ac.hit.weather.model.WeatherData;
import il.ac.hit.weather.exception.WeatherDataServiceException;

public class FiveDaysWeatherGui
{
	private int i = 0;
	
	private Date tempDate;
	
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("d/MM");
	
	private ImagePanel imgPanel;
	
	private IWeatherDataService forecastService = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceFactory.OPEN_WEATHER_MAP_5_DAYS);
	private IWeatherDataService currentService = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceFactory.OPEN_WEATHER_MAP_CURRENT);
	
	private List<WeatherData> weatherDataList;
	private WeatherData weatherData;
	
	private NumberFormat numberFormat = NumberFormat.getInstance();
	private final Color transparent = new Color(0, 0, 0, 0);
	
	private JFrame mainFrame, cityFrame;
	private JPanel insideTabPanel, weatherPanel, currentWeatherPanel, leftPanel, rightPanel, smallPanel;
	private JTabbedPane tabPane;
	private JTextField cityTextField;
	private JButton goButton, changeCityButton;
	
	private JLabel firstLabel, secondLabel, errorLabel;
	private JLabel iconLabel, 
					weatherDescLabel,
					tempLabel, 
					windLabel, 
					cloudinessLabel,
					pressureLabel,
					humidityLabel,
					timeLabel,
					rightTitleLabel;
	
	private JLabel currentIconLabel, 
					currentWeatherDescLabel,
					currentLabel, 
					currentTempLabel, 
					currentWindLabel, 
					currentCloudinessLabel,
					currentPressureLabel,
					currentHumidityLabel,
					currentCityLabel;
	
	// Constructor that creates first window
	public FiveDaysWeatherGui()
	{
		cityFrame = new JFrame("OpenWeatherMap");
		cityFrame.setResizable(false);
		
		imgPanel =  new ImagePanel(new ImageIcon("icons/blue.jpg").getImage());
		imgPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints i = new GridBagConstraints();
		
		cityFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		firstLabel = new JLabel("OpenWeatherMap Project");
		firstLabel.setFont(new Font("Arial", Font.BOLD, 40));
		firstLabel.setForeground(Color.WHITE);
		
		secondLabel = new JLabel("Get weather in:");
		secondLabel.setFont(new Font("Arial", Font.BOLD, 30));
		secondLabel.setForeground(Color.WHITE);
		
		cityTextField = new JTextField(7);
		cityTextField.setFont(new Font("Arial", Font.BOLD, 35));
		cityTextField.setForeground(Color.WHITE);
		cityTextField.setBackground(transparent);
		cityTextField.setOpaque(false);

		goButton = new JButton("Go");
		goButton.setForeground(Color.WHITE);
		goButton.setBackground(transparent);
		goButton.setOpaque(false);
		goButton.setFont(new Font("Arial", Font.BOLD, 30));
		goButton.setFocusPainted(false);
		
		errorLabel = new JLabel("City not found");
		errorLabel.setFont(new Font("Arial", Font.BOLD, 30));
		errorLabel.setForeground(Color.WHITE);
		errorLabel.setVisible(false);
		
		cityFrame.add(imgPanel);
		
		i.gridx = 0;
		i.gridy = 0;
		i.gridwidth = 2;
		i.insets = new Insets(0, 0, 100, 0);
		imgPanel.add(firstLabel, i);
		
		i.gridx = 0;
		i.gridy = 1;
		i.gridwidth = 2;
		i.insets = new Insets(0, 0, 0, 0);
		imgPanel.add(secondLabel, i);
		
		i.gridx = 0;
		i.gridy = 2;
		i.gridwidth = 0;
		i.insets = new Insets(0, 0, 10, 0);
		imgPanel.add(cityTextField, i);
		
		i.gridx = 0;
		i.gridy = 3;
		i.gridwidth = 3;
		i.insets = new Insets(0, 0, 10, 0);
		imgPanel.add(goButton, i);
		
		i.gridx = 0;
		i.gridy = 4;
		i.gridwidth = 3;
		i.insets = new Insets(0, 0, 10, 0);
		imgPanel.add(errorLabel, i);
		
		cityFrame.setSize(new Dimension(imgPanel.getWidth(), imgPanel.getHeight()));
		
		goButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					weatherDataList = forecastService.getWeatherData(new Location(0, cityTextField.getText(), null));
					weatherData = currentService.getWeatherData(new Location(0, cityTextField.getText(), null)).get(0);
					
					createMainFrame();
					
					mainFrame.pack();
					mainFrame.setLocationRelativeTo(null);
					mainFrame.setVisible(true);
					cityFrame.setVisible(false);
				}
				catch(WeatherDataServiceException ex)
				{
					errorLabel.setVisible(true);
				}
			}
		});
	}
	
	// Creating second window with weather
	private void createMainFrame()
	{
			numberFormat.setMaximumFractionDigits(2);
			
			imgPanel =  new ImagePanel(new ImageIcon("icons/blue.jpg").getImage());
			imgPanel.setLayout(new GridLayout(0, 2));
			
			mainFrame = new JFrame("5 Days Weather");
			mainFrame.setResizable(false);
			mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			
			currentWeatherPanelCreating();
			forecastWeatherPanelCreating();
			
			mainFrame.add(imgPanel);
			imgPanel.add(leftPanel);
			imgPanel.add(rightPanel);
			
			mainFrame.setPreferredSize(new Dimension(imgPanel.getWidth(), imgPanel.getHeight()));
		
	}

	// Creating the right side panel with 7 days forecast
	private void forecastWeatherPanelCreating()
	{
		rightPanel = new JPanel();
		rightPanel.setLayout(new GridBagLayout());
		rightPanel.setOpaque(false);
		rightPanel.setBackground(transparent);
		
		rightTitleLabel = new JLabel("5 Days forecast");
		rightTitleLabel.setFont(new Font("Arial", Font.BOLD, 25));
		rightTitleLabel.setForeground(Color.WHITE);
		
		insideTabPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 0;
		
		insideTabPanel.setBackground(transparent);
		
		tabbedPaneStyleInit();
		
		tabPane = new JTabbedPane(JTabbedPane.TOP);
		tabPane.setFont(new Font("Arial", Font.BOLD, 15));
		tabPane.setForeground(Color.WHITE);
		
		tempDate = weatherDataList.get(0).getDate();
		
		for(i = 0; i < weatherDataList.size(); i++)
		{
			if(dateFormat.format(weatherDataList.get(i).getDate()).equals(dateFormat.format(tempDate)))
			{
				weatherPanel = new JPanel();
				weatherPanel.setLayout(new BoxLayout(weatherPanel, BoxLayout.LINE_AXIS));
				weatherPanel.setBackground(new Color(255, 255, 255, 50));
				
				smallPanel = new JPanel();
				smallPanel.setLayout(new BoxLayout(smallPanel, BoxLayout.Y_AXIS));
				smallPanel.setBackground(new Color(255, 255, 255, 50));
				
				timeLabel = new JLabel("  " + timeFormat.format(weatherDataList.get(i).getDate()) + "  ");
				timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
				timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				timeLabel.setForeground(Color.WHITE);
				
				iconLabel = new JLabel(new ImageIcon("icons/"+weatherDataList.get(i).getWeatherIcon()+".png"));
				iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				tempLabel = new JLabel("  " + numberFormat.format(weatherDataList.get(i).getMainTemp()) + " \u00b0" + "C  ");
				tempLabel.setFont(new Font("Arial", Font.BOLD, 20));
				tempLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				tempLabel.setForeground(Color.WHITE);
				
				weatherDescLabel = new JLabel(weatherDataList.get(i).getWeatherDescription().substring(0, 1).toUpperCase() + weatherDataList.get(i).getWeatherDescription().substring(1) + "  ");
				weatherDescLabel.setFont(new Font("Arial", Font.BOLD, 15));
				weatherDescLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				weatherDescLabel.setForeground(Color.WHITE);;
				
				windLabel = new JLabel("Wind: " + numberFormat.format(weatherDataList.get(i).getWindSpeed()) + " m/s");
				windLabel.setFont(new Font("Arial", Font.BOLD, 10));
				windLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				windLabel.setForeground(Color.WHITE);
				
				cloudinessLabel = new JLabel("Cloudiness: " + weatherDataList.get(i).getCloudiness() + " %");
				cloudinessLabel.setFont(new Font("Arial", Font.BOLD, 10));
				cloudinessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				cloudinessLabel.setForeground(Color.WHITE);
				
				pressureLabel = new JLabel("Pressure: " + weatherDataList.get(i).getMainPressure() + " hpa");
				pressureLabel.setFont(new Font("Arial", Font.BOLD, 10));
				pressureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				pressureLabel.setForeground(Color.WHITE);
				
				humidityLabel = new JLabel("Humidity: " + weatherDataList.get(i).getMainHumidity() + " %");
				humidityLabel.setFont(new Font("Arial", Font.BOLD, 10));
				humidityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				humidityLabel.setForeground(Color.WHITE);
				
				weatherPanel.add(timeLabel);
				weatherPanel.add(iconLabel);
				weatherPanel.add(tempLabel);
				weatherPanel.add(weatherDescLabel);
				
				smallPanel.add(windLabel);
				smallPanel.add(cloudinessLabel);
				smallPanel.add(pressureLabel);
				smallPanel.add(humidityLabel);
				
				c.gridy = i;
				c.gridx = 0;
				insideTabPanel.add(weatherPanel, c);
				
				c.gridy = i;
				c.gridx = 1;
				insideTabPanel.add(smallPanel, c);
			}
			else
			{
				tabPane.addTab(dateFormat.format(weatherDataList.get(i-1).getDate()), insideTabPanel);
				
				insideTabPanel = new JPanel(new GridBagLayout());
				insideTabPanel.setBackground(transparent);
				
				tempDate = weatherDataList.get(i).getDate();
				i--;
			}
		}
		
		tabPane.addTab(dateFormat.format(weatherDataList.get(i-1).getDate()), insideTabPanel);
		
		GridBagConstraints r = new GridBagConstraints();
		
		r.fill = GridBagConstraints.VERTICAL;
		r.insets = new Insets(0, 0, 15, 0);
		r.gridx = 0;
		r.gridy = 0;
		rightPanel.add(rightTitleLabel, r);
		r.gridx = 0;
		r.gridy = 1;
		rightPanel.add(tabPane, r);
	}

	private void tabbedPaneStyleInit()
	{
		UIManager.put("TabbedPane.selected", new Color(255, 255, 255, 50));
		UIManager.put("TabbedPane.darkShadow", transparent);
		UIManager.put("TabbedPane.light", transparent);
		UIManager.put("TabbedPane.unselectedBackground", transparent);
		UIManager.put("TabbedPane.selectHighlight", transparent);
		UIManager.put("TabbedPane.tabAreaBackground", transparent);
		UIManager.put("TabbedPane.borderHightlightColor", transparent);
		UIManager.put("TabbedPane.contentOpaque", false);
		UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
	}

	// Creating the left side panel with current weather
	private void currentWeatherPanelCreating()
	{
		currentWeatherPanel = new JPanel();
		currentWeatherPanel.setLayout(new BoxLayout(currentWeatherPanel, BoxLayout.Y_AXIS));
		currentWeatherPanel.setBackground(new Color(255, 255, 255, 50));
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setOpaque(false);
		leftPanel.setBackground(transparent);
		
		currentIconLabel = new JLabel(new ImageIcon("icons/"+weatherData.getWeatherIcon()+".png"));
		currentIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		currentLabel = new JLabel("Current weather");
		currentLabel.setFont(new Font("Arial", Font.BOLD, 25));
		currentLabel.setForeground(Color.WHITE);
		
		currentTempLabel = new JLabel(numberFormat.format(weatherData.getMainTemp()) + " \u00b0" + "C");
		currentTempLabel.setFont(new Font("Arial", Font.BOLD, 25));
		currentTempLabel.setForeground(Color.WHITE);
		currentTempLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		currentWeatherDescLabel = new JLabel(weatherData.getWeatherDescription().substring(0, 1).toUpperCase() + weatherData.getWeatherDescription().substring(1));
		currentWeatherDescLabel.setFont(new Font("Arial", Font.BOLD, 25));
		currentWeatherDescLabel.setForeground(Color.WHITE);
		currentWeatherDescLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		currentWindLabel = new JLabel("Wind: " + numberFormat.format(weatherData.getWindSpeed()) + " m/s");
		currentWindLabel.setFont(new Font("Arial", Font.BOLD, 23));
		currentWindLabel.setForeground(Color.WHITE);
		currentWindLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		currentCloudinessLabel = new JLabel("Cloudiness: " + weatherData.getCloudiness() + " %");
		currentCloudinessLabel.setFont(new Font("Arial", Font.BOLD, 23));
		currentCloudinessLabel.setForeground(Color.WHITE);
		currentCloudinessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		currentPressureLabel = new JLabel("Pressure: " + weatherData.getMainPressure() + " hpa");
		currentPressureLabel.setFont(new Font("Arial", Font.BOLD, 23));
		currentPressureLabel.setForeground(Color.WHITE);
		currentPressureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		currentHumidityLabel = new JLabel("Humidity: " + weatherData.getMainHumidity() + " %");
		currentHumidityLabel.setFont(new Font("Arial", Font.BOLD, 23));
		currentHumidityLabel.setForeground(Color.WHITE);
		currentHumidityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		currentCityLabel = new JLabel(weatherData.getLocationJSON());
		currentCityLabel.setFont(new Font("Arial", Font.BOLD, 30));
		currentCityLabel.setForeground(Color.WHITE);
		
		//----------------------------------------------------------------
		
		currentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		leftPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		leftPanel.add(currentLabel);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		
		currentCityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftPanel.add(currentCityLabel);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		
		currentWeatherPanel.add(currentIconLabel);
		currentWeatherPanel.add(Box.createRigidArea(new Dimension(0,20)));
		currentWeatherPanel.add(currentTempLabel);
		currentWeatherPanel.add(Box.createRigidArea(new Dimension(0,20)));
		currentWeatherPanel.add(currentWeatherDescLabel);
		currentWeatherPanel.add(Box.createRigidArea(new Dimension(0,20)));
		currentWeatherPanel.add(currentWindLabel);
		currentWeatherPanel.add(Box.createRigidArea(new Dimension(0,20)));
		currentWeatherPanel.add(currentCloudinessLabel);
		currentWeatherPanel.add(Box.createRigidArea(new Dimension(0,20)));
		currentWeatherPanel.add(currentPressureLabel);
		currentWeatherPanel.add(Box.createRigidArea(new Dimension(0,20)));
		currentWeatherPanel.add(currentHumidityLabel);
		currentWeatherPanel.add(Box.createRigidArea(new Dimension(300,20)));
		
		currentWeatherPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		changeCityButton = new JButton("Change city");
		changeCityButton.setForeground(Color.WHITE);
		changeCityButton.setBackground(transparent);
		changeCityButton.setOpaque(false);
		changeCityButton.setFont(new Font("Arial", Font.BOLD, 20));
		changeCityButton.setFocusPainted(false);
		changeCityButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		changeCityButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				cityFrame.setVisible(true);
				mainFrame.setVisible(false);
			}
		});
		
		leftPanel.add(currentWeatherPanel);
		leftPanel.add(Box.createRigidArea(new Dimension(0,20)));
		leftPanel.add(changeCityButton);
	}

	// Show the first window
	public void show()
	{
		cityFrame.pack();
		cityFrame.setLocationRelativeTo(null);
		cityFrame.setVisible(true);
	}
}
