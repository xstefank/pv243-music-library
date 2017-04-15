package cz.muni.fi.pv243.musiclib.util;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public class LuceneQueryUtil {

    public static Query createFuzzyFieldQuery(FullTextEntityManager entityManager, Class entityType
            , String fieldName, String searchExpr) {
        QueryBuilder queryBuilder = entityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(entityType).get();
        return createFuzzyFieldQuery(queryBuilder, fieldName, searchExpr);
    }

    public static Query createFuzzyFieldQuery(QueryBuilder queryBuilder, String fieldName, String searchExpr) {
        return queryBuilder
                .keyword()
                .fuzzy()
                .withPrefixLength(1)
                .withEditDistanceUpTo(2)
                .onField(fieldName)
                .matching(searchExpr)
                .createQuery();
    }

}
