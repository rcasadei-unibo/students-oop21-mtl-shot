package view;

import javafx.scene.image.ImageView;
import model.character.Character;
import util.DirectionHorizontal;
import util.Vector2D;
import util.map.MapConstants;
import util.view.Animation;

/**
 * TODO: javadoc.
 */
public class CharacterView {

	public final Animation characterIdle;
	public final Animation characterIdleUp;
	public final Animation characterRun;
	public final Animation characterRunUp;
	public final Animation characterCrouchIdle;
	public final Animation characterCrouchRun;
	public final ImageView characterImageView = new ImageView();
	public final Vector2D imageOffset = new Vector2D(0.5,0.5);
   
	/**
     * 
     */ 
	public CharacterView(
			final Animation characterIdle,
			final Animation characterIdleUp,
			final Animation characterRun,
			final Animation characterRunUp,
			final Animation characterCrouchIdle,
			final Animation characterCrouchRun
			) {
		this.characterIdle = characterIdle;
		this.characterIdleUp = characterIdleUp;
		this.characterRun = characterRun;
		this.characterRunUp = characterRunUp;
		this.characterCrouchIdle = characterCrouchIdle;
		this.characterCrouchRun = characterCrouchRun;
	}
	
	

    /**
     * 
     * @param position
     * @param crouch
     * @param direction
     */
    public void updateCharacter(final Character character) {
    	final var adjustY = character.isCrouching() ? character.getHitbox().getY() : 0d;
        characterImageView.setX((character.getPosition().getX() - imageOffset.getX()) * MapConstants.getTilesize());
        characterImageView.setY((character.getPosition().getY() - imageOffset.getY() - adjustY) * MapConstants.getTilesize());

    	if(character.getSpeed().getX() == 0) {
    		switch (character.getAim().getDirection().getY()) {
	        case UP:
	        	switch (character.getAim().getDirection().getX()) {
	        		case LEFT:
	    	        	characterImageView.setImage(characterIdleUp.get(false));
	    	        	characterIdleUp.animate();
	        			break;
	        		case RIGHT:
	    	        	characterImageView.setImage(characterIdleUp.get(true));
	    	        	characterIdleUp.animate();
	        			break;
	        	}
	        	break;
	        case NEUTRAL:
	        	switch (character.getAim().getDirection().getX()) {
	        		case LEFT:
	    	        	characterImageView.setImage(characterIdle.get(false));
	    	        	characterIdle.animate();
	        			break;
	        		case RIGHT:
	    	        	characterImageView.setImage(characterIdle.get(true));
	    	        	characterIdle.animate();
	        			break;
	        	}
	        	break;
	        case DOWN:
	        	switch (character.getAim().getDirection().getX()) {
	        		case LEFT:
	    	        	characterImageView.setImage(characterCrouchIdle.get(false));
	    	        	characterCrouchIdle.animate();
	        			break;
	        		case RIGHT:
	    	        	characterImageView.setImage(characterCrouchIdle.get(true));
	    	        	characterCrouchIdle.animate();
	        			break;
	        	}
	        	break;
	        	
	        }
            if(character.isCrouching() && character.getAim().getDirection().getX().equals(DirectionHorizontal.LEFT)) {
            	characterImageView.setImage(characterCrouchIdle.get(false));
            	characterCrouchIdle.animate();
            } else if(character.isCrouching() && character.getAim().getDirection().getX().equals(DirectionHorizontal.RIGHT)) {
            	characterImageView.setImage(characterCrouchIdle.get(true));
            	characterCrouchIdle.animate();
            }
    	} else if (character.getSpeed().getX() != 0) {
    		switch (character.getAim().getDirection().getY()) {
	        case UP:
	        	switch (character.getAim().getDirection().getX()) {
	        		case LEFT:
	    	        	characterImageView.setImage(characterRunUp.get(false));
	    	        	characterRunUp.animate();
	        			break;
	        		case RIGHT:
	    	        	characterImageView.setImage(characterRunUp.get(true));
	    	        	characterRunUp.animate();
	        			break;
	        	}
	        	break;
	        case NEUTRAL:
	        	switch (character.getAim().getDirection().getX()) {
	        		case LEFT:
	    	        	characterImageView.setImage(characterRun.get(false));
	    	        	characterRun.animate();
	        			break;
	        		case RIGHT:
	    	        	characterImageView.setImage(characterRun.get(true));
	    	        	characterRun.animate();
	        			break;
	        	}
	        	break;
	        case DOWN:
	        	switch (character.getAim().getDirection().getX()) {
	        		case LEFT:
	    	        	characterImageView.setImage(characterCrouchRun.get(false));
	    	        	characterCrouchRun.animate();
	        			break;
	        		case RIGHT:
	    	        	characterImageView.setImage(characterCrouchRun.get(true));
	    	        	characterCrouchRun.animate();
	        			break;
	        	}
	        	break;
	        }    
    	}
    	if(character.isCrouching() && character.getAim().getDirection().getX().equals(DirectionHorizontal.LEFT)) {
        	characterImageView.setImage(characterCrouchRun.get(false));
        	characterCrouchRun.animate();
        } else if(character.isCrouching() && character.getAim().getDirection().getX().equals(DirectionHorizontal.RIGHT)) {
        	characterImageView.setImage(characterCrouchRun.get(true));
        	characterCrouchRun.animate();
        }
    }

	/**
     * 
     * @return bla
     */
    public ImageView getCharacterImageView() {
        return characterImageView;
    }

}
