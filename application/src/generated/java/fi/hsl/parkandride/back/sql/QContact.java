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
 * QContact is a Querydsl query type for QContact
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QContact extends RelationalPathSpatial<QContact> {

    private static final long serialVersionUID = 1851015157;

    public static final QContact contact = new QContact("CONTACT");

    public final StringPath cityEn = createString("cityEn");

    public final StringPath cityFi = createString("cityFi");

    public final StringPath citySv = createString("citySv");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath infoEn = createString("infoEn");

    public final StringPath infoFi = createString("infoFi");

    public final StringPath infoSv = createString("infoSv");

    public final StringPath nameEn = createString("nameEn");

    public final StringPath nameFi = createString("nameFi");

    public final StringPath nameSv = createString("nameSv");

    public final StringPath openingHoursEn = createString("openingHoursEn");

    public final StringPath openingHoursFi = createString("openingHoursFi");

    public final StringPath openingHoursSv = createString("openingHoursSv");

    public final NumberPath<Long> operatorId = createNumber("operatorId", Long.class);

    public final SimplePath<fi.hsl.parkandride.core.domain.Phone> phone = createSimple("phone", fi.hsl.parkandride.core.domain.Phone.class);

    public final StringPath postalCode = createString("postalCode");

    public final StringPath streetAddressEn = createString("streetAddressEn");

    public final StringPath streetAddressFi = createString("streetAddressFi");

    public final StringPath streetAddressSv = createString("streetAddressSv");

    public final com.querydsl.sql.PrimaryKey<QContact> constraint6 = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<QOperator> contactOperatorIdFk = createForeignKey(operatorId, "ID");

    public final com.querydsl.sql.ForeignKey<QFacility> _facilityOperatorContactIdFk = createInvForeignKey(id, "OPERATOR_CONTACT_ID");

    public final com.querydsl.sql.ForeignKey<QFacility> _facilityServiceContactIdFk = createInvForeignKey(id, "SERVICE_CONTACT_ID");

    public final com.querydsl.sql.ForeignKey<QFacility> _facilityEmergencyContactIdFk = createInvForeignKey(id, "EMERGENCY_CONTACT_ID");

    public QContact(String variable) {
        super(QContact.class, forVariable(variable), "PUBLIC", "CONTACT");
        addMetadata();
    }

    public QContact(String variable, String schema, String table) {
        super(QContact.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QContact(Path<? extends QContact> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "CONTACT");
        addMetadata();
    }

    public QContact(PathMetadata metadata) {
        super(QContact.class, metadata, "PUBLIC", "CONTACT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(cityEn, ColumnMetadata.named("CITY_EN").withIndex(14).ofType(Types.VARCHAR).withSize(255));
        addMetadata(cityFi, ColumnMetadata.named("CITY_FI").withIndex(12).ofType(Types.VARCHAR).withSize(255));
        addMetadata(citySv, ColumnMetadata.named("CITY_SV").withIndex(13).ofType(Types.VARCHAR).withSize(255));
        addMetadata(email, ColumnMetadata.named("EMAIL").withIndex(7).ofType(Types.VARCHAR).withSize(255));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(infoEn, ColumnMetadata.named("INFO_EN").withIndex(20).ofType(Types.VARCHAR).withSize(255));
        addMetadata(infoFi, ColumnMetadata.named("INFO_FI").withIndex(18).ofType(Types.VARCHAR).withSize(255));
        addMetadata(infoSv, ColumnMetadata.named("INFO_SV").withIndex(19).ofType(Types.VARCHAR).withSize(255));
        addMetadata(nameEn, ColumnMetadata.named("NAME_EN").withIndex(4).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(nameFi, ColumnMetadata.named("NAME_FI").withIndex(2).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(nameSv, ColumnMetadata.named("NAME_SV").withIndex(3).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(openingHoursEn, ColumnMetadata.named("OPENING_HOURS_EN").withIndex(17).ofType(Types.VARCHAR).withSize(2000));
        addMetadata(openingHoursFi, ColumnMetadata.named("OPENING_HOURS_FI").withIndex(15).ofType(Types.VARCHAR).withSize(2000));
        addMetadata(openingHoursSv, ColumnMetadata.named("OPENING_HOURS_SV").withIndex(16).ofType(Types.VARCHAR).withSize(2000));
        addMetadata(operatorId, ColumnMetadata.named("OPERATOR_ID").withIndex(5).ofType(Types.BIGINT).withSize(19));
        addMetadata(phone, ColumnMetadata.named("PHONE").withIndex(6).ofType(Types.VARCHAR).withSize(255));
        addMetadata(postalCode, ColumnMetadata.named("POSTAL_CODE").withIndex(11).ofType(Types.VARCHAR).withSize(5));
        addMetadata(streetAddressEn, ColumnMetadata.named("STREET_ADDRESS_EN").withIndex(10).ofType(Types.VARCHAR).withSize(255));
        addMetadata(streetAddressFi, ColumnMetadata.named("STREET_ADDRESS_FI").withIndex(8).ofType(Types.VARCHAR).withSize(255));
        addMetadata(streetAddressSv, ColumnMetadata.named("STREET_ADDRESS_SV").withIndex(9).ofType(Types.VARCHAR).withSize(255));
    }

}

