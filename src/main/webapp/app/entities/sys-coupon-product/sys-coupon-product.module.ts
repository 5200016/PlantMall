import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysCouponProductComponent,
    SysCouponProductDetailComponent,
    SysCouponProductUpdateComponent,
    SysCouponProductDeletePopupComponent,
    SysCouponProductDeleteDialogComponent,
    sysCouponProductRoute,
    sysCouponProductPopupRoute
} from './';

const ENTITY_STATES = [...sysCouponProductRoute, ...sysCouponProductPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysCouponProductComponent,
        SysCouponProductDetailComponent,
        SysCouponProductUpdateComponent,
        SysCouponProductDeleteDialogComponent,
        SysCouponProductDeletePopupComponent
    ],
    entryComponents: [
        SysCouponProductComponent,
        SysCouponProductUpdateComponent,
        SysCouponProductDeleteDialogComponent,
        SysCouponProductDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysCouponProductModule {}
