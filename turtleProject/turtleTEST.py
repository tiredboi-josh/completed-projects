import turtle
import time
import playsound
playsound('')

wn = turtle.Screen()
peepo = turtle.Turtle()
peepo.frame = 0 # keeps track of withch frame the peepo is on
peepo.penup() # turtle no draw
turtle.bgpic('space.png')


def shape_register():

    #this function registers each frame of the peepo gif i am using 
    
    turtle.register_shape('left_0.gif')
    turtle.register_shape('left_1.gif')
    turtle.register_shape('left_2.gif')
    turtle.register_shape('left_3.gif')
    turtle.register_shape('left_4.gif')
    turtle.register_shape('left_5.gif')
    turtle.register_shape('left_6.gif')


    turtle.register_shape('right_0.gif')
    turtle.register_shape('right_1.gif')
    turtle.register_shape('right_2.gif')
    turtle.register_shape('right_3.gif')
    turtle.register_shape('right_4.gif')
    turtle.register_shape('right_5.gif')
    turtle.register_shape('right_6.gif')


    turtle.register_shape('up_0.gif')
    turtle.register_shape('up_1.gif')
    turtle.register_shape('up_2.gif')
    turtle.register_shape('up_3.gif')
    turtle.register_shape('up_4.gif')
    turtle.register_shape('up_5.gif')
    turtle.register_shape('up_6.gif')


    turtle.register_shape('down_0.gif')
    turtle.register_shape('down_1.gif')
    turtle.register_shape('down_2.gif')
    turtle.register_shape('down_3.gif')
    turtle.register_shape('down_4.gif')
    turtle.register_shape('down_5.gif')
    turtle.register_shape('down_6.gif')




holding_key = 0

def holding_left():
    holding_key = 0
    print('left')
    while holding_key == 0:
        peepo_left_animation()

        

def release_left():
    holding_key = 0





def peepo_left_animation():
    
    
    peepo.leftframes = ['left_0.gif' , 'left_1.gif' ,'left_2.gif' ,'left_3.gif' , 'left_4.gif' , 'left_5.gif' , 'left_6.gif' ] # list of all the frames for left movment 

    peepo.frame += 1 # next frame used for list

    if peepo.frame >= len(peepo.leftframes): # make sure that the frame number dosent extened outside the length of the list
        peepo.frame = 0

    peepo.shape(peepo.leftframes[peepo.frame]) # sets the shape of the turtle pen to the current peepo frame

    peepo.seth(180) # sets the direction of the pointer so it can move in the right direction
    peepo.fd(10)

    

    #wn.ontimer(peepo_left_animation, 200) # dont worry about this


def peepo_right_animation():
    
    
    peepo.rightframes = ['right_0.gif' , 'right_1.gif' ,'right_2.gif' ,'right_3.gif' , 'right_4.gif' , 'right_5.gif' , 'right_6.gif' ] # list of all the frames for right movement

    peepo.frame += 1 # next frame used for list

    if peepo.frame >= len(peepo.rightframes): # make sure that the frame number dosent extened outside the length of the list
        peepo.frame = 0

    peepo.shape(peepo.rightframes[peepo.frame]) # sets the shape of the turtle pen to the current peepo frame


    peepo.seth(0) # sets the direction of the pointer so it can move in the right direction
    peepo.fd(10)

    #wn.ontimer(peepo_right_animation, 100) # dont worry about this




def peepo_up_animation():
    
    
    peepo.upframes = ['up_0.gif' , 'up_1.gif' ,'up_2.gif' ,'up_3.gif' , 'up_4.gif' , 'up_5.gif' , 'up_6.gif' ] # list of all the frames for up movement

    peepo.frame += 1 # next frame used for list of frames

    if peepo.frame >= len(peepo.upframes): # sets the shape of the turtle pen to the current peepo frame
        peepo.frame = 0

    peepo.shape(peepo.upframes[peepo.frame]) # sets the turtle pen to the correct frame

    peepo.seth(90) # sets the direction of the pointer so it can move in the right direction
    peepo.fd(10)

    #wn.ontimer(peepo_up_animation, 200) # dont worry about this
    


def peepo_down_animation():
    
    
    peepo.downframes = ['down_0.gif' , 'down_1.gif' ,'down_2.gif' ,'down_3.gif' , 'down_4.gif' , 'down_5.gif' , 'down_6.gif' ] # list of all the frames for down movement

    peepo.frame += 1

    if peepo.frame >= len(peepo.downframes): # sets the shape of the turtle pen to the current peepo frame
        peepo.frame = 0

    peepo.shape(peepo.downframes[peepo.frame]) # sets the turtle pen to the correct frame

    peepo.seth(270) # sets the direction of the pointer so it can move in the right direction
    peepo.fd(10)

    # time.sleep(0.1) # dont worry about this
    # peepo_down_animation()
    
    #wn.ontimer(peepo_down_animation, 200)


shape_register() # calls the function to tell turtle what images i am using


# user controls

wn.onkeypress(holding_left , "Left")
wn.onkeyrelease(release_left , "Left")

wn.onkey(peepo_right_animation , "Right")
wn.onkey(peepo_up_animation , "Up")
wn.onkey(peepo_down_animation , "Down")
wn.listen()




while True: # keeps the screen updated
    wn.update()

    
    



wn.mainloop() # gets the screen displayed




