import smtplib
from email import encoders
from email.mime.text import MIMEText
from email.mime.base import MIMEBase
from email.mime.multipart import MIMEMultipart
import os

from secrets import email, password, to_email, image

server = smtplib.SMTP('smtp.gmail.com', 25)

server.ehlo()

server.login(email, password)

msg = MIMEMultipart()

msg['From'] = email
msg['To'] = to_email
msg['Subject'] = 'Just a test'

message ='Hello World.'

msg.attach(MIMEText(message, 'plain'))

filename = image
attachment = open(filename, 'rb')

p = MIMEBase('application', 'octet-stream')
p.set_payload(attachment.read())

encoders.encode_base64(p)
p.add_header('Content-Disposition', f'attachment; filename={filename}')
msg.attach(p)
# sda
text = msg.as_string()
server.sendmail(email, to_email, text)