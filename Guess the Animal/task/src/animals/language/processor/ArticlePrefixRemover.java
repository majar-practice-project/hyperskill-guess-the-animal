package animals.language.processor;

public class ArticlePrefixRemover implements StringProcessor{
    private final String ARTICLE;

    public ArticlePrefixRemover(String[] articles){
        assert(articles.length!=0) : "shouldn't use remover if you don't need to remove anything";

        StringBuilder sb = new StringBuilder("(?i)^(");
        sb.append(articles[0]);
        sb.append('|');
        for (String article : articles) {
            sb.append('|').append(article);
        }
        sb.append(')');
        ARTICLE = sb.toString();
    }
    @Override
    public String process(String text) {
        return text.replaceAll(ARTICLE, "");
    }
}
