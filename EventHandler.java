/* 
    Handles real life events, like disasters...
    Soon to be implemented.

    by Daniel Li
    5/27/20
*/

public class EventHandler{
    public EventHandler(){
    }

    public String indShock(StoreItem_DL item){
        double coinflip = Math.random();
        // 50/50 chance of getting a demand shock in any of these products
        if (coinflip > 0.8){
            double change = Math.random() + 0.2;
            // headlines associated with positive demand shocks
            String[] posHeadlines = {
                    String.format("A new study shows that %s is very beneficial to health",item.getName()),
                    String.format("TikTok stars are promoting %s like crazy!",item.getName()),
                    String.format("No one knows why, but all of the sudden, owning %s is the wave!",item.getName()),
                    String.format("Reports show that prices of %s are going to skyrocket in the future!",item.getName())
                    };

            // headlines associated with negative demand shocks
            String[] negHeadlines = {
                    String.format("A new study shows that %s is very harmful to health",item.getName()),
                    String.format("TikTok star denounces %s",item.getName()),
                    String.format("Who even likes %s anymore? Was it just a fad?",item.getName()),
                    String.format("Reports show that prices of %s are going to plummet in the future!",item.getName())
                    };
            // change pop indexes

            if(change > 1){
                if(item.getPIndex() * change > 1){
                    item.setPIndex(1);
                }
                else{
                    item.incrementPIndex(change);
                }
                int seed = (int)(Math.random() * (posHeadlines.length - 1));
                return posHeadlines[seed];
            }
            else{
                item.incrementPIndex(change);
                int seed = (int)(Math.random() * (negHeadlines.length - 1));
                return negHeadlines[seed];
            }
        }
        // returns nothing if there's no news, this sucks because it generates a lot of unnecessary whitespace. 
        else{
            String[] neutralHeadlines = {
                "Dog escapes from shelter to save the world",
                "Studies show that for every 60 seconds that go by in Europe, a minute passes",
                "Local man saves cat from tree",
                "Local man saves dog from tree",
                "Local woman saves cat from tree",
                "Local man saves cat from tree",
                "Eating food may be a good cure for hunger, studies show"
                };
            int seed = (int)(Math.random() * (neutralHeadlines.length - 1));
            //return neutralHeadlines[seed];
            return "";
        }

    }
    // demand shocks that affect the all goods
    public String marketShock(StoreItem_DL[] items){
        double change = Math.random() + 0.2;
        // headlines, might need to add a txt file with a bunch of these
        String[] negHeadlines = {
                "A pandemic has caused the government to discourage shopping",
                "A financial recession has hit and is underway",
                "A new non-materialistic religion has surfaced and is gaining traction!",
                "Federal income tax has been raised, spending power down.",
                "A decline in population of the local town means there will be less shoppers demanding products"};

        String[] posHeadlines = {
                "Government imposes shopping rebates",
                "Economic growth is underway!",
                "A new cult has arisen that praises the acquisition of material goods",
                "Federal income tax has been lowered, Americans spending like crazy!",
                "A boost in population of the local town means there will be more shoppers demanding products"};
        
        // how much to change the popularity indexes by?
        if(change > 0){
            for(int i =0; i < items.length; i++){
                StoreItem_DL item = items[i];
                if(change > 0){
                    if(item.getPIndex() * change > 1){
                        item.setPIndex(1);
                    }
                    else{
                        item.incrementPIndex(change);
                    }   
                }
            }
            int seed = (int)(Math.random() * (posHeadlines.length - 1));
            return posHeadlines[seed];   
        }
        else{
            for(int i =0; i < items.length; i++){
                StoreItem_DL item = items[i];
                item.incrementPIndex(change);
            }
            int seed = (int)(Math.random() * (negHeadlines.length - 1));
            return negHeadlines[seed];
        }
    }
}
