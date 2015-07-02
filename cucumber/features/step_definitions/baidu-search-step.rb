Given 'I am on the Baidu search page' do
  @browser.get 'http://www.baidu.com/'
end

When /I search for "(.*)"/ do |query|
  @browser.find_element(:id, "kw").send_keys query
  @browser.find_element(:id, "su").click
end

Then /I should see "(.*)"/ do |text|
  sleep 2
  expect(@browser.find_element(:id, "content_left").text).to include(text)
end