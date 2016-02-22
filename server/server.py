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
    for contact in secret.admins:
      sms = client.messages.create(to=secret.numbers[contact],
          from_=secret.from_num, body="Battery Low")
      return "got it"

  open("state.txt", 'w').close()
  f = open("state.txt", 'w')
  f.write(situation)

  for num in secret.numbers.itervalues():
    sms = client.messages.create(to=num, from_=secret.from_num, body=situation)

  return "messages sent"


@app.route('/read')
def data():
  f = "state.txt"
  with open (f, "r") as myfile:
        data=myfile.read().strip()
  html = "<html><body><h1>%s</h1></body></html>" % (data)
  return html


app.run(host="0.0.0.0", debug=True, threaded=True)

