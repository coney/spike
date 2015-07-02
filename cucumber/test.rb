#!/usr/bin/env ruby

require 'selenium-webdriver'
require 'awesome_print'

Selenium::WebDriver::Firefox::path='/opt/homebrew-cask/Caskroom/firefox/38.0.5/Firefox.app/Contents/MacOS/firefox'

driver = Selenium::WebDriver.for(:firefox)

driver.get("http://www.baidu.com/")

ap "start baidu"

search_box = driver.find_element(:id, "kw");

ap "get input box"

search_box.send_keys("huawei");

ap "sent key"

driver.find_element(:id, "su").click

ap  driver.find_element(:id, "content_left").text

sleep 5
driver.close
driver.quit