package tilegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Creature {
	
	//Animations
	private Animation animStand, animDown, animUp, animLeft, animRight, animZDown, animZUp, animZLeft, animZRight;
	// Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	//inventory
	private Inventory inventory;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 19;
		bounds.height = 19;
		
		//Animatons
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);
		animStand = new Animation(500, Assets.player_stand);
		animZDown = new Animation(500, Assets.zombie_down);
		animZUp = new Animation(500, Assets.zombie_up);
		animZLeft = new Animation(500, Assets.zombie_left);
		animZRight = new Animation(500, Assets.zombie_right);
		
		inventory = new Inventory(handler);
	}

	@Override
	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		animStand.tick();
		//Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		// Attack
		checkAttacks();
		// Inventory
		inventory.tick();
		
		if (handler.getKeyManager().space) {
			State.setState(handler.getGame().menuState);
		}
	}
	
	private void checkAttacks(){
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		
		if(inventory.isActive())
			return;
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}else if(handler.getKeyManager().aDown){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}else if(handler.getKeyManager().aLeft){
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else if(handler.getKeyManager().aRight){
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else{
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)){
				//
				e.hurt(1.5);
				//
				return;
			}
		}
		
	}
	
	@Override
	public void die(){
		
	}
	
	private void getInput(){
		xMove = 0;
		yMove = 0;
		xAttack = 0;
		yAttack = 0;
		
		if(inventory.isActive())
			return;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
		if(handler.getKeyManager().aUp)
			yAttack = attack;
		if(handler.getKeyManager().aDown)
			yAttack = -attack;
		if(handler.getKeyManager().aLeft)
			xAttack = -attack;
		if(handler.getKeyManager().aRight)
			xAttack = attack;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		Text.drawString(g, "Your Health:" + health, 320, 12, true, Color.GREEN, Assets.font24);
		Text.drawString(g, "PRESS SPACE TO PAUSE", 540, 12, true, Color.GREEN, Assets.font12);
		if(health == 10) {
			g.drawImage(Assets.heart, 430, 4, 15, 15, null);
		}else {
			g.drawImage(Assets.heart, 420, 4, 15, 15, null);
		}
	}
	
	public void postRender(Graphics g){
		inventory.render(g);
		if (health <= 0) {
			Text.drawString(g, "WASTED", 400, 200, true, Color.RED, Assets.font76);
			Text.drawString(g, "You were punched to death", 400, 260, true, Color.RED, Assets.font28);
		}
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		if(xMove < 0){
			return animLeft.getCurrentFrame();
		}else if(xMove > 0){
			return animRight.getCurrentFrame();
		}else if(yMove < 0){
			return animUp.getCurrentFrame();
		}else if(yMove > 0){
			return animDown.getCurrentFrame();
		}else if(xAttack < 0){
			return animZLeft.getCurrentFrame();
		}else if(xAttack > 0){
			return animZRight.getCurrentFrame();
		}else if(yAttack < 0){
			return animZDown.getCurrentFrame();
		}else if(yAttack > 0){
			return animZUp.getCurrentFrame();
		}else {
			return animStand.getCurrentFrame();
		}
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}