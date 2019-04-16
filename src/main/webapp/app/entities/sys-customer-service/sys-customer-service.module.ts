import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysCustomerServiceComponent,
    SysCustomerServiceDetailComponent,
    SysCustomerServiceUpdateComponent,
    SysCustomerServiceDeletePopupComponent,
    SysCustomerServiceDeleteDialogComponent,
    sysCustomerServiceRoute,
    sysCustomerServicePopupRoute
} from './';

const ENTITY_STATES = [...sysCustomerServiceRoute, ...sysCustomerServicePopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysCustomerServiceComponent,
        SysCustomerServiceDetailComponent,
        SysCustomerServiceUpdateComponent,
        SysCustomerServiceDeleteDialogComponent,
        SysCustomerServiceDeletePopupComponent
    ],
    entryComponents: [
        SysCustomerServiceComponent,
        SysCustomerServiceUpdateComponent,
        SysCustomerServiceDeleteDialogComponent,
        SysCustomerServiceDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysCustomerServiceModule {}
