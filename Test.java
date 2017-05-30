package test;

import java.util.ArrayList;

import processing.core.*;
import remixlab.bias.event.MotionEvent;
import remixlab.dandelion.constraint.Constraint;
import remixlab.dandelion.core.Eye;
import remixlab.dandelion.geom.Frame;
import remixlab.dandelion.geom.Rotation;
import remixlab.dandelion.geom.Vec;
import remixlab.proscene.*;

public class Test extends PApplet {
	  
	  float x=1,y=1, fri = (float) 0.001;
	  int rackx = 250; 
	  int racky = 0;
	  int ancho = 800;
	  int largo = 400;
	  int mAncho = ancho/5;
	  int mLargo = largo/5;
	  int oX = ancho - mAncho;
	  int oY = largo - mLargo;
	  boolean showMiniMap  = true;
	  
	  
	ArrayList<InteractiveFrame> bolas = new ArrayList<InteractiveFrame>();
	InteractiveFrame taco;
	Scene scene;
	//Choose FX2D, JAVA2D, P2D or P3D
	String renderer = OPENGL;
	PShape s;
	float radio = 10;
	
	Vec[] vels = {
				new Vec(10, 0 ,0),
			  new Vec(0, 0,0), 
			  new Vec(0, 0,0), 
			  new Vec(0, 0,0), 
			  new Vec(0, 0,0), 
			  new Vec(0, 0,0), 
			  new Vec(0, 0,0), 
			  new Vec(0, 0,0), 
			  new Vec(0, 0,0), 
			  new Vec(0, 0,0), 
			  new Vec(0, 0,0), 
			  new Vec(0, 0,0), 
			  new Vec(0, 0,0), 
			  new Vec(0, 0,0), 
			  new Vec(0, 0,0), 
			  new Vec(0, 0,0)
			  
			  };
	
	
    public static void main(String[] args) {
        PApplet.main("test.Test");

    }
    
    public void settings(){
  	  size(900, 600, renderer);

    }
  
  public void setup() {
	  frameRate(60);
	  TableroConstraint t = new TableroConstraint();
	  TacoConstraint t2 = new TacoConstraint();
	  
	  scene = new Scene(this);
	  scene.setGridVisualHint(false);
	  System.out.println(scene.eye().distanceToAnchor());
	  scene.eye().setSceneRadius(400);
	  for (int i=0;i<16;i++){
		  PShape s;
		  s = createShape(SPHERE, radio);
		  s.setFill(obtenerColor(i));
		  s.setStroke(false);
		  s.setSpecular(CENTER);
		  bolas.add(new InteractiveFrame(scene, s));
		  bolas.get(i).setConstraint(t);
		  bolas.get(i).setPosition(obtenerPos(i));
		  bolas.get(i).setMagnitude((float) (radio*.1));
	  }
	 taco = new InteractiveFrame(scene, bolas.get(0));
	 taco.setShape(crearTaco());
	 taco.setPosition(-300,0, radio );
	 taco.rotate(10, 10, 10, 10);
	 taco.setConstraint(t2);
    }
  
  public void draw() {
	  background(0);
	  dibujarMesa();
	  pointLight(255, 255, 255, 0, 0, 400);
	  
	  
	  taco.draw();
	  
	  for (int i=0;i<16;i++){
		  bolas.get(i).translate(vels[i]);
		  bolas.get(i).draw();
	  }
	  
  }
  
  public void keyPressed() {
	  if(scene.eyeFrame().damping() == 0)
	    scene.eyeFrame().setDamping((float) 0.5);
	  else
	    scene.eyeFrame().setDamping(0);
	  println("Camera damping friction now is " + scene.eyeFrame().damping());
	}
 
  
  int obtenerColor(int bola){
	  int codigoColor = 0;
	  switch(bola)
	    {
	    case 0:
	    	codigoColor = color(255);
	    	break;
	    case 1: 
	      codigoColor = color(242,242,24);
	      break;
	    case 2: 
	      codigoColor = color(20,27,201);
	      break;
	    case 3: 
	      codigoColor = color(229,37,2);
	      break;
	    case 4: 
	      codigoColor = color(160,44,133);
	      break;
	    case 5: 
	      codigoColor = color(255,173,8);
	      break;
	    case 6: 
	      codigoColor = color(27,167,20);
	      break;
	    case 7: 
	      codigoColor = color(129,26,1);
	      break;
	    case 8: 
	      codigoColor = color(0,0,0);
	      break;
	    case 9: 
	      codigoColor = color(242,242,24);
	      break;
	    case 10: 
	      codigoColor = color(20,27,201);
	      break;
	    case 11: 
	      codigoColor = color(229,37,2);
	      break;
	    case 12: 
	      codigoColor = color(160,44,133);
	      break;
	    case 13: 
	      codigoColor = color(255,173,8);
	      break;
	    case 14: 
	      codigoColor = color(27,167,20);
	      break;
	    case 15: 
	      codigoColor = color(129,26,1);
	      break;
	    }

	  return codigoColor;
  }
  
  Vec obtenerPos(int bola){
	  Vec p = new Vec();
	  switch (bola) {
	  case 1 : p.set(rackx, racky, radio); break; 
	  case 2 : p.set(rackx+sqrt(400), racky-10, radio); break; 
	  case 3 : p.set(rackx+sqrt(400), racky+10, radio); break; 
	  case 4 : p.set(rackx+(2*sqrt(400)), racky-20, radio); break; 
	  case 5 : p.set(rackx+(2*sqrt(400)), racky+0, radio); break; 
	  case 6 : p.set(rackx+(2*sqrt(400)), racky+20, radio); break; 
	  case 7 : p.set(rackx+(3*sqrt(400)), racky-30, radio); break; 
	  case 8 : p.set(rackx+(3*sqrt(400)), racky+10, radio); break; 
	  case 9 : p.set(rackx+(3*sqrt(400)), racky+30, radio); break; 
	  case 10 : p.set(rackx+(3*sqrt(400)), racky-10, radio); break; 
	  case 11 : p.set(rackx+(4*sqrt(400)), racky+0, radio); break; 
	  case 12 : p.set(rackx+(4*sqrt(400)), racky+20, radio); break; 
	  case 13 : p.set(rackx+(4*sqrt(400)), racky+40, radio); break; 
	  case 14 : p.set(rackx+(4*sqrt(400)), racky-20, radio); break; 
	  case 15 : p.set(rackx+(4*sqrt(400)), racky-40, radio); break; 
	  case 0 : p.set(-250, 0, radio); break;
	default:
		break;
	}
  
	  
	  
	  return p;
  }
  
  PShape crearTaco( ){
	  int sides = 10;
	  int h = 200;
	  int r1 = 1;
	  int r2 = 3;
	  PShape taco = createShape(GROUP);
	  PShape top = createShape();
	  PShape bottom = createShape();
	  PShape body = createShape();
	  
      float angle = 360 / sides;
      float halflargo = h / 2;
      // top
      top.beginShape();
      for (int i = 0; i < sides; i++) {
          float x = cos( radians( i * angle ) ) * r1;
          float y = sin( radians( i * angle ) ) * r1;
          top.vertex( x, y, -halflargo);
      }
      top.endShape(CLOSE);
      // bottom
      bottom.beginShape();
      for (int i = 0; i < sides; i++) {
          float x = cos( radians( i * angle ) ) * r2;
          float y = sin( radians( i * angle ) ) * r2;
          bottom.vertex( x, y, halflargo);
      }
      bottom.endShape(CLOSE);
      // draw body
      body.beginShape(TRIANGLE_STRIP);
      body.noStroke();
      body.fill(222,184,135);
      for (int i = 0; i < sides + 1; i++) {
          float x1 = cos( radians( i * angle ) ) * r1;
          float y1 = sin( radians( i * angle ) ) * r1;
          float x2 = cos( radians( i * angle ) ) * r2;
          float y2 = sin( radians( i * angle ) ) * r2;
          body.vertex( x1, y1, -halflargo);
          body.vertex( x2, y2, halflargo);
      }
      body.endShape(CLOSE);
      
      taco.addChild(top);
      taco.addChild(bottom);
      taco.addChild(body);
      
      
      return taco;
  } 
    
  
  class TableroConstraint extends Constraint{
		public Vec constrainTranslation(Vec p, Frame ball){
			p.setZ(0);
			if (ball.position().x() > ancho/2 - radio) {
				ball.setPosition(ancho/2-radio, ball.position().y(), radio);
				p.setX(p.get().x() * -1);
    		  }
			if (ball.position().x() < -ancho/2 + radio) {
				ball.setPosition(-ancho/2+radio, ball.position().y(), radio);
				p.setX(p.get().x() * -1);
    		  }
			if (ball.position().y() > largo/2 - radio) {
				ball.setPosition(ball.position().x(),largo/2-radio, radio);
				p.setY(p.get().y() * -1);
    		  }
			if (ball.position().y() < -largo/2 + radio) {
				ball.setPosition(ball.position().x(),-largo/2+radio, radio);
				p.setY(p.get().y() * -1);
    		  }
			
			for(int i=0; i<16; i++){
				  int current = bolas.indexOf(ball);
				  if (i != current){
					  checkCollision((InteractiveFrame) ball, bolas.get(i));
				  }
				  
			}
			
			return p;
		}

  }
  
  class TacoConstraint extends Constraint{
		public Vec constrainTranslation(Vec p, Frame taco){

			return p;
		}
		
		public Rotation constrainRotation(Rotation r, Frame taco){
			System.out.println(r.angle());
			
			return r;
		}

  }
  
  void dibujarMesa(){
	  PShape mesa = createShape(GROUP);
	  PShape tablero = createShape(RECT ,-ancho/2, -largo/2, ancho, largo);
	  tablero.setFill(color(17,64,24));
	  
	  PShape borde2 = createShape();
	  borde2.beginShape(QUAD_STRIP);
	  borde2.noStroke();
	  borde2.fill(color(17,64,24));
	  
	  borde2.vertex(ancho/2, -largo/2, 0);
	  borde2.vertex(ancho/2, -largo/2, radio*2);
	  
	  borde2.vertex(-ancho/2, -largo/2, 0);
	  borde2.vertex(-ancho/2, -largo/2, radio*2);
	  
	  borde2.vertex(-ancho/2, largo/2, 0);
	  borde2.vertex(-ancho/2, largo/2, radio*2);
	  
	  borde2.vertex(ancho/2, largo/2, 0);
	  borde2.vertex(ancho/2, largo/2, radio*2);
	  
	  borde2.vertex(ancho/2, -largo/2, 0);
	  borde2.vertex(ancho/2, -largo/2, radio*2);
	  
	  borde2.endShape();
	  
	  mesa.addChild(tablero);
	  mesa.addChild(borde2);
	  
	  shape(mesa);
	  
  }
  
  
  void checkCollision(InteractiveFrame ball, InteractiveFrame other) {
	  	
	  	int current = bolas.indexOf(ball);
	  	int next = bolas.indexOf(other);
	    // Get distances between the balls components
	    Vec distanceVect = Vec.subtract(other.position(), ball.position());
	    				
	    // Calculate magnitude of the vector separating the balls
	    float distanceVectMag = distanceVect.magnitude();

	    // Minimum distance before they are touching
	    float minDistance = radio + radio;

	    if (distanceVectMag < minDistance) {
	      float distanceCorrection = (float) ((minDistance-distanceVectMag)/2.0);
	      Vec d = distanceVect.get();
	      d.normalize();
	      d.multiply(distanceCorrection);
	      Vec correctionVector = d;
	      other.position().add(correctionVector);
	      ball.position().subtract(correctionVector);

	      // get angle of distanceVect
	      float theta  = distanceVect.heading();
	      // precalculate trig values
	      float sine = sin(theta);
	      float cosine = cos(theta);

	      /* bTemp will hold rotated ball positions. You 
	       just need to worry about bTemp[1] position*/
	      PVector[] bTemp = {
	        new PVector(), new PVector()
	      };

	      /* this ball's position is relative to the other
	       so you can use the vector between them (bVect) as the 
	       reference point in the rotation expressions.
	       bTemp[0].position.x and bTemp[0].position.y will initialize
	       automatically to 0.0, which is what you want
	       since b[1] will rotate around b[0] */
	      bTemp[1].x  = cosine * distanceVect.x() + sine * distanceVect.y();
	      bTemp[1].y  = cosine * distanceVect.y() - sine * distanceVect.x();

	      // rotate Temporary velocities
	      PVector[] vTemp = {
	        new PVector(), new PVector()
	      };

	      vTemp[0].x  = cosine * vels[current].x() + sine * vels[current].y();
	      vTemp[0].y  = cosine * vels[current].y() - sine * vels[current].x();
	      vTemp[1].x  = cosine * vels[next].x() + sine * vels[next].y();
	      vTemp[1].y  = cosine * vels[next].y() - sine * vels[next].x();

	      /* Now that velocities are rotated, you can use 1D
	       conservation of momentum equations to calculate 
	       the final velocity along the x-axis. */
	      PVector[] vFinal = {  
	        new PVector(), new PVector()
	      };

	      // final rotated velocity for b[0]
	      vFinal[0].x = ((ball.magnitude() - other.magnitude()) * vTemp[0].x + 2 * other.magnitude() * vTemp[1].x) / (ball.magnitude() + other.magnitude());
	      vFinal[0].y = vTemp[0].y;

	      // final rotated velocity for b[0]
	      vFinal[1].x = ((other.magnitude() - ball.magnitude()) * vTemp[1].x + 2 * ball.magnitude() * vTemp[0].x) / (ball.magnitude() + other.magnitude());
	      vFinal[1].y = vTemp[1].y;

	      // hack to avoid clumping
	      bTemp[0].x += vFinal[0].x;
	      bTemp[1].x += vFinal[1].x;

	      /* Rotate ball positions and velocities back
	       Reverse signs in trig expressions to rotate 
	       in the opposite direction */
	      // rotate balls
	      Vec[] bFinal = { 
	        new Vec(), new Vec()
	      };

	      bFinal[0].setX( cosine * bTemp[0].x - sine * bTemp[0].y);
	      bFinal[0].setY( cosine * bTemp[0].y + sine * bTemp[0].x);
	      bFinal[1].setX( cosine * bTemp[1].x - sine * bTemp[1].y);
	      bFinal[1].setY( cosine * bTemp[1].y + sine * bTemp[1].x);

	      // update balls to screen position
	      other.position().setX( ball.position().x() + bFinal[1].x());
	      other.position().setY( ball.position().y() + bFinal[1].y());

	      ball.position().add(bFinal[0]);

	      // update velocities
	      vels[current].setX( cosine * vFinal[0].x - sine * vFinal[0].y);
	      vels[current].setY(cosine * vFinal[0].y + sine * vFinal[0].x);
	      vels[next].setX(cosine * vFinal[1].x - sine * vFinal[1].y);
	      vels[next].setY(cosine * vFinal[1].y + sine * vFinal[1].x);
	    }
	  }
  
  void checkVels(){
	  for(int i=0; i<16; i++){ 
		  vels[i].setX((float) (vels[i].x() - 0.0000001));
		  vels[i].setY((float) (vels[i].y() - 0.0000001));
  	}
	  for(int i=0; i<16; i++){ 
		  if(vels[i].x()<0.2){
			  vels[i].setX(0);
		  }
		  if(vels[i].y()<0.1){
			  vels[i].setY(0);
		  }
  	}
  }
  
}