import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysCouponComponent,
    SysCouponDetailComponent,
    SysCouponUpdateComponent,
    SysCouponDeletePopupComponent,
    SysCouponDeleteDialogComponent,
    sysCouponRoute,
    sysCouponPopupRoute
} from './';

const ENTITY_STATES = [...sysCouponRoute, ...sysCouponPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysCouponComponent,
        SysCouponDetailComponent,
        SysCouponUpdateComponent,
        SysCouponDeleteDialogComponent,
        SysCouponDeletePopupComponent
    ],
    entryComponents: [SysCouponComponent, SysCouponUpdateComponent, SysCouponDeleteDialogComponent, SysCouponDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysCouponModule {}
