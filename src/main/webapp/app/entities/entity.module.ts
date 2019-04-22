import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { PlantMallSysUserModule } from './sys-user/sys-user.module';
import { PlantMallSysAdminModule } from './sys-admin/sys-admin.module';
import { PlantMallSysRoleModule } from './sys-role/sys-role.module';
import { PlantMallSysMemberSettingModule } from './sys-member-setting/sys-member-setting.module';
import { PlantMallSysMemberLevelModule } from './sys-member-level/sys-member-level.module';
import { PlantMallSysReceiverAddressModule } from './sys-receiver-address/sys-receiver-address.module';
import { PlantMallSysProductModule } from './sys-product/sys-product.module';
import { PlantMallSysClassifyModule } from './sys-classify/sys-classify.module';
import { PlantMallSysCustomerServiceModule } from './sys-customer-service/sys-customer-service.module';
import { PlantMallSysBannerModule } from './sys-banner/sys-banner.module';
import { PlantMallSysOrderModule } from './sys-order/sys-order.module';
import { PlantMallSysShoppingCarModule } from './sys-shopping-car/sys-shopping-car.module';
import { PlantMallSysReviewModule } from './sys-review/sys-review.module';
import { PlantMallSysCollectionModule } from './sys-collection/sys-collection.module';
import { PlantMallSysRechargeModule } from './sys-recharge/sys-recharge.module';
import { PlantMallSysPlantLogModule } from './sys-plant-log/sys-plant-log.module';
import { PlantMallSysProductImageModule } from './sys-product-image/sys-product-image.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        PlantMallSysUserModule,
        PlantMallSysAdminModule,
        PlantMallSysRoleModule,
        PlantMallSysMemberSettingModule,
        PlantMallSysMemberLevelModule,
        PlantMallSysReceiverAddressModule,
        PlantMallSysProductModule,
        PlantMallSysClassifyModule,
        PlantMallSysCustomerServiceModule,
        PlantMallSysBannerModule,
        PlantMallSysOrderModule,
        PlantMallSysShoppingCarModule,
        PlantMallSysReviewModule,
        PlantMallSysCollectionModule,
        PlantMallSysRechargeModule,
        PlantMallSysPlantLogModule,
        PlantMallSysProductImageModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallEntityModule {}
