from flask import Flask, Response, request
from twilio.rest import TwilioRestClient
import secret

client = TwilioRestClient(secret.account_sid, secret.auth_token)

app = Flask(import_name="powerdetectorserver")

@app.route('/', methods=["POST", "GET"])
def root():
  situation = request.form['situation']
  print situation

  if situation == "LOW":
    for contact in ["jack", "chris"]:
      sms = client.messages.create(to=secret.numbers[contact],
          from_=secret.from_num, body="Battery Low")
      return "got it"

  open("itai.txt", 'w').close()
  f = open("itai.txt", 'w')
  f.write(situation)
  if situation == "CONNECTED":
    message = "Itai in the house!"
  if situation == "DISCONNECTED":
    message = "Itai is gone"

  for num in secret.numbers.itervalues():
    sms = client.messages.create(to=num, from_=secret.from_num, body=message)

  return "messages sent"


@app.route('/read')
def data():
  f = "itai.txt"
  with open (f, "r") as myfile:
        data=myfile.read().strip()
  if data == "CONNECTED":
    response = "Itai is in the house!"
    image = "itaihappy.jpg"
    width = "320"
    height = "240"

  else:
    data == "DISCONNECTED"
    response = "Itai is gone :("
    image = "itaisad.jpg"
    width = "255"
    height = "330"

  html = "<html><body><h1>%s</h1><br><div style=\"text-align: center; margin: 0 auto; \"> <img width=\"%s\" height=\"%s\" src=\"http://jackfischer.me/%s\"></div></body></html>" % (response, width, height, image)
  return html


app.run(host="0.0.0.0", debug=True, threaded=True)

