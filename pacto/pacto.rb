require 'pacto'
require 'pacto/rspec'

WebMock.allow_net_connect!

Pacto.configure do |c|
  c.contracts_path = 'contracts'
end

Pacto.generate!

require 'net/http'

Net::HTTP.start('www.baidu.com', 80) {|http|
  response = http.get('/')
  puts response.body
}

# readme = Octokit.readme 'thoughtworks/pacto'
#
# puts readme.encoding