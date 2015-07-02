begin require 'rspec/expectations'; rescue LoadError; require 'spec/expectations'; end
browser = nil

require 'selenium-webdriver'
Selenium::WebDriver::Firefox::path='/opt/homebrew-cask/Caskroom/firefox/38.0.5/Firefox.app/Contents/MacOS/firefox'
browser = Selenium::WebDriver.for(:firefox)

Before do
  @browser = browser
end

# "after all"
at_exit do
  browser.close
  browser.quit
end