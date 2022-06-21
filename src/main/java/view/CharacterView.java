package view;

import javafx.scene.image.ImageView;
import model.character.Character;
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
    public void updateCharacter(Character character) {
        characterImageView.setX(character.getPosition().getX() * MapConstants.getTilesize());
        characterImageView.setY(character.getPosition().getY() * MapConstants.getTilesize());
    	if(character.getSpeed().getX() == 0) {
    		
    		switch (character.getAim().getDirection()) {
	        case UP:
	        	characterImageView.setImage(characterIdleUp.get(false));
	        	characterIdleUp.animate();
	        	break;
	        case DOWN:
	            characterImageView.setImage(characterCrouchIdle.get(false)); // DA CAMBIAREEE
	            characterCrouchIdle.animate();
	            break;
	        case RIGHT:
	            characterImageView.setImage(characterIdle.get(true));
	            characterIdle.animate();
	            break;
	        case LEFT:
	            characterImageView.setImage(characterIdle.get(false));
	            characterIdle.animate();
	            break;
	        case NEUTRAL:
	        default:
	            break;
	        }
	        if (character.isCrouching()) {
	            characterImageView.setImage(characterCrouchIdle.get(true));
	            characterCrouchIdle.animate();
	        }
	        
    	} else if (character.getSpeed().getX() > 0) {
    		
    		switch (character.getAim().getDirection()) {
	        case UP:
	        	characterImageView.setImage(characterRunUp.get(true));
	        	characterRunUp.animate();
	        	break;
	        case DOWN:
	            characterImageView.setImage(characterCrouchRun.get(true));
	            characterCrouchRun.animate();
	            break;
	        case RIGHT:
	            characterImageView.setImage(characterRun.get(true));
	            characterRun.animate();
	            break;
	        case LEFT:
	            characterImageView.setImage(characterRun.get(false));
	            characterRun.animate();
	            break;
	        case NEUTRAL:
	        default:
	            break;
	        }
	        if (character.isCrouching()) {
	            characterImageView.setImage(characterCrouchIdle.get(true));
	            characterCrouchIdle.animate();
	        }
	        
    	} else if (character.getSpeed().getX() < 0) {
    		
    		switch (character.getAim().getDirection()) {
    		 case UP:
 	        	characterImageView.setImage(characterRunUp.get(false));
 	        	characterRunUp.animate();
 	        	break;
 	        case DOWN:
 	            characterImageView.setImage(characterCrouchRun.get(false));
 	            characterCrouchRun.animate();
 	            break;
 	        case LEFT:
 	            characterImageView.setImage(characterRun.get(false));
 	            characterRun.animate();
 	            break;
 	        case RIGHT:
	            characterImageView.setImage(characterRun.get(true));
	            characterRun.animate();
	            break;
 	        case NEUTRAL:
	        default:
	            break;
	        }
	        if (character.isCrouching()) {
	            characterImageView.setImage(characterCrouchIdle.get(false));
	            characterCrouchIdle.animate();
	        }
	        
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
