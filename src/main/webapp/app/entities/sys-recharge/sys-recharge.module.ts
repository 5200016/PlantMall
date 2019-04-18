import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysRechargeComponent,
    SysRechargeDetailComponent,
    SysRechargeUpdateComponent,
    SysRechargeDeletePopupComponent,
    SysRechargeDeleteDialogComponent,
    sysRechargeRoute,
    sysRechargePopupRoute
} from './';

const ENTITY_STATES = [...sysRechargeRoute, ...sysRechargePopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysRechargeComponent,
        SysRechargeDetailComponent,
        SysRechargeUpdateComponent,
        SysRechargeDeleteDialogComponent,
        SysRechargeDeletePopupComponent
    ],
    entryComponents: [SysRechargeComponent, SysRechargeUpdateComponent, SysRechargeDeleteDialogComponent, SysRechargeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysRechargeModule {}
