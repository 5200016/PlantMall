import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysCouponClassifyComponent,
    SysCouponClassifyDetailComponent,
    SysCouponClassifyUpdateComponent,
    SysCouponClassifyDeletePopupComponent,
    SysCouponClassifyDeleteDialogComponent,
    sysCouponClassifyRoute,
    sysCouponClassifyPopupRoute
} from './';

const ENTITY_STATES = [...sysCouponClassifyRoute, ...sysCouponClassifyPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysCouponClassifyComponent,
        SysCouponClassifyDetailComponent,
        SysCouponClassifyUpdateComponent,
        SysCouponClassifyDeleteDialogComponent,
        SysCouponClassifyDeletePopupComponent
    ],
    entryComponents: [
        SysCouponClassifyComponent,
        SysCouponClassifyUpdateComponent,
        SysCouponClassifyDeleteDialogComponent,
        SysCouponClassifyDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysCouponClassifyModule {}
