/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes;

import parsers.frc.managers.Type;

/**
 *
 * @author Dennis
 */
public class Result {
    private String result;
        private Type type;
        public Result(String result, Type type){
            this.result=result;
            this.type = type;
        }

        /**
         * @return the result
         */
        public String getResult() {
            return result;
        }

        /**
         * @param result the result to set
         */
        public void setResult(String result) {
            this.result = result;
        }

        /**
         * @return the type
         */
        public Type getType() {
            return type;
        }

        /**
         * @param type the type to set
         */
        public void setType(Type type) {
            this.type = type;
        }
        
    @Override
    public String toString(){
        return result;
    }
}
