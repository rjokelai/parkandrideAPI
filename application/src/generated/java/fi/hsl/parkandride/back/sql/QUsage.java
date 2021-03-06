package fi.hsl.parkandride.back.sql;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;

import com.querydsl.sql.spatial.RelationalPathSpatial;

import com.querydsl.spatial.*;



/**
 * QUsage is a Querydsl query type for QUsage
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QUsage extends RelationalPathSpatial<QUsage> {

    private static final long serialVersionUID = -1491957066;

    public static final QUsage usage = new QUsage("USAGE");

    public final StringPath name = createString("name");

    public final com.querydsl.sql.PrimaryKey<QUsage> constraint4 = createPrimaryKey(name);

    public final com.querydsl.sql.ForeignKey<QFacilityPrediction> _facilityPredictionUsageFk = createInvForeignKey(name, "USAGE");

    public final com.querydsl.sql.ForeignKey<QUnavailableCapacityHistory> _unavailableCapacityHistoryUsageFk = createInvForeignKey(name, "USAGE");

    public final com.querydsl.sql.ForeignKey<QPricing> _pricingUsageFk = createInvForeignKey(name, "USAGE");

    public final com.querydsl.sql.ForeignKey<QFacilityUtilization> _facilityUtilizationUsageFk = createInvForeignKey(name, "USAGE");

    public final com.querydsl.sql.ForeignKey<QUnavailableCapacity> _unavailableCapacityUsageFk = createInvForeignKey(name, "USAGE");

    public final com.querydsl.sql.ForeignKey<QPredictor> _predictorUsageFk = createInvForeignKey(name, "USAGE");

    public QUsage(String variable) {
        super(QUsage.class, forVariable(variable), "PUBLIC", "USAGE");
        addMetadata();
    }

    public QUsage(String variable, String schema, String table) {
        super(QUsage.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QUsage(Path<? extends QUsage> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "USAGE");
        addMetadata();
    }

    public QUsage(PathMetadata metadata) {
        super(QUsage.class, metadata, "PUBLIC", "USAGE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(name, ColumnMetadata.named("NAME").withIndex(1).ofType(Types.VARCHAR).withSize(64).notNull());
    }

}

