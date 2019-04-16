import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysReceiverAddressComponent,
    SysReceiverAddressDetailComponent,
    SysReceiverAddressUpdateComponent,
    SysReceiverAddressDeletePopupComponent,
    SysReceiverAddressDeleteDialogComponent,
    sysReceiverAddressRoute,
    sysReceiverAddressPopupRoute
} from './';

const ENTITY_STATES = [...sysReceiverAddressRoute, ...sysReceiverAddressPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysReceiverAddressComponent,
        SysReceiverAddressDetailComponent,
        SysReceiverAddressUpdateComponent,
        SysReceiverAddressDeleteDialogComponent,
        SysReceiverAddressDeletePopupComponent
    ],
    entryComponents: [
        SysReceiverAddressComponent,
        SysReceiverAddressUpdateComponent,
        SysReceiverAddressDeleteDialogComponent,
        SysReceiverAddressDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysReceiverAddressModule {}
