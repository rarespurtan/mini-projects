from tkinter import *
from tkinter import ttk, colorchooser, filedialog
import PIL
from PIL import ImageGrab

class main:
    def __init__(self, master):
        self.master = master
        self.colour_fg = "black"
        self.colour_bg = "white"
        self.old_x = None
        self.old_y = None
        self.penwidth = 5
        self.drawWidgets()
        self.c.bind("<B1-Motion>", self.paint)
        self.c.bind("<ButtonRelease-1>", self.reset)

    def paint(self, event):
        if self.old_x and self.old_y:
            self.c.create_line(self.old_x, 
                            self.old_y, event.x, event.y, 
                            width=self.penwidth, 
                            fill=self.colour_fg, 
                            capstyle=ROUND, 
                            smooth=True)
        self.old_x = event.x
        self.old_y = event.y

    def reset(self, event):
        self.old_x = None
        self.old_y = None

    def changeW(self, event):
        self.penwidth = event
    
    def save(self):
        file=filedialog.asksaveasfilename(filetypes=[("Portable Network Graphics", "*.png")])
        if file:
            x = self.master.winfo_rootx() + self.c.winfo_x()
            y = self.master.winfo_rooty() + self.c.winfo_y()
            x1 = x + self.c.winfo_width()
            y1 = y + self.c.winfo_height()

            PIL.ImageGrab.grab().crop((x,y,x1,y1)).save(file + ".png")

    def clear(self):
        self.c.delete("all")

    def change_bg(self):
        self.colour_bg = colorchooser.askcolor(color=self.colour_bg)[1]   
        self.c["bg"] = self.colour_bg
        

    def change_fg(self):
        self.colour_fg = colorchooser.askcolor(color=self.colour_fg)[1] 
        

    def drawWidgets(self):
        self.controls = Frame(self.master, padx=5, pady=5)
        Label(self.controls, text="Pen Width", font =("",15)).grid(row=0, column=0)
        self.slider = ttk.Scale(self.controls, from_=5, to=100, command=self.changeW, orient=HORIZONTAL)
        self.slider.set(self.penwidth)
        self.slider.grid(row=0, column=1, ipadx=30)
        self.controls.pack()

        self.c = Canvas(self.master, width=500, height=400, bg=self.colour_bg)
        self.c.pack(fill=BOTH, expand=True)    

        menu = Menu(self.master)
        self.master.config(menu=menu)
        filemenu= Menu(menu)
        menu.add_cascade(label="File..", menu=filemenu)
        filemenu.add_command(label="Export..", command=self.save)

        colourmenu = Menu(menu)
        menu.add_cascade(label="Colours..", menu=colourmenu)
        colourmenu.add_command(label="Brush Colour..", command=self.change_fg)
        colourmenu.add_command(label="Background Colour..", command=self.change_bg)

        optionmenu= Menu(menu)
        menu.add_cascade(label="Options..", menu=optionmenu)
        optionmenu.add_command(label="Clear Canvas..", command=self.clear)
        optionmenu.add_command(label="Exit..", command=self.master.destroy)

if __name__=='__main__':
    root = Tk()
    main(root)
    root.title("Ms_Paint_Clone")  
    root.mainloop()      