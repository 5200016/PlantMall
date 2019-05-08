import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysCouponUserComponent,
    SysCouponUserDetailComponent,
    SysCouponUserUpdateComponent,
    SysCouponUserDeletePopupComponent,
    SysCouponUserDeleteDialogComponent,
    sysCouponUserRoute,
    sysCouponUserPopupRoute
} from './';

const ENTITY_STATES = [...sysCouponUserRoute, ...sysCouponUserPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysCouponUserComponent,
        SysCouponUserDetailComponent,
        SysCouponUserUpdateComponent,
        SysCouponUserDeleteDialogComponent,
        SysCouponUserDeletePopupComponent
    ],
    entryComponents: [
        SysCouponUserComponent,
        SysCouponUserUpdateComponent,
        SysCouponUserDeleteDialogComponent,
        SysCouponUserDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysCouponUserModule {}
