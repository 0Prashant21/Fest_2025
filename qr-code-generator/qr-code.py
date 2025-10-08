from requests import * 
from tkinter import * 
from PIL import Image, ImageTk
import io

root=Tk()
root.geometry("1000x600+290+90")
root.title("QR Generator")
f=("Arial",30,"bold")

def gen():
    link=ent.get()
    url= f"https://api.qrserver.com/v1/create-qr-code/?size=150x150&data={link}"
    response=get(url)
    # Convert response content into an image
    image_data = Image.open(io.BytesIO(response.content))
    res = ImageTk.PhotoImage(image_data)

    lab.configure(image=res)
    lab.image=res

lab_msg = Label(root,text="Enter URL link",font=f)
lab_msg.pack(pady=10)
ent = Entry(root,font=f,width=30)
ent.pack(pady=10)
btn = Button(root,text="Generate URL",font=f,command=gen)
btn.pack(pady=10)
lab = Label(root,font=f)
lab.pack(pady=10)

root.mainloop()