$LOAD_PATH << "."

require 'wsapp'

Rack::Handler::Thin.run Sinatra::Application, :Port => 8000
