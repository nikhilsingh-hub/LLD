package DesignPatterns.Builder.DbQueryConstruction;

import DesignPatterns.Builder.MessagingService.Message;
import DesignPatterns.Builder.WithBuilder;
import Inheritance.B;

@WithBuilder
public class Query {
    private String select;
    private String from;
    private String where;
    private String join;
    private String orderBy;
    private String groupBy;

    public Query(String select, String from, String where, String join, String orderBy, String groupBy) {
        this.select = select;
        this.from = from;
        this.where = where;
        this.join = join;
        this.orderBy = orderBy;
        this.groupBy = groupBy;
    }

    public String getSelect() {
        return select;
    }

    public String getFrom() {
        return from;
    }

    public String getWhere() {
        return where;
    }

    public String getJoin() {
        return join;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public static class Builder{
        private String select;
        private String from;
        private String where;
        private String join;
        private String orderBy;
        private String groupBy;

        public Builder select(String s){
            this.select=s;
            return this;
        }
        public Builder from(String s){
            this.from=s;
            return this;
        }
        public Builder where(String s){
            this.where=s;
            return this;
        }
        public Builder join(String s){
            this.join=s;
            return this;
        }
        public Builder orderBy(String s){
            this.orderBy=s;
            return this;
        }
        public Builder groupBy(String s){
            this.groupBy=s;
            return this;
        }
        public Query build(){
            return new Query(this.select, this.from, this.where, this.join, this.orderBy, this.groupBy);
        }
    }
}
