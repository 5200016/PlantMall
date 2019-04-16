import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysShoppingCarComponent,
    SysShoppingCarDetailComponent,
    SysShoppingCarUpdateComponent,
    SysShoppingCarDeletePopupComponent,
    SysShoppingCarDeleteDialogComponent,
    sysShoppingCarRoute,
    sysShoppingCarPopupRoute
} from './';

const ENTITY_STATES = [...sysShoppingCarRoute, ...sysShoppingCarPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysShoppingCarComponent,
        SysShoppingCarDetailComponent,
        SysShoppingCarUpdateComponent,
        SysShoppingCarDeleteDialogComponent,
        SysShoppingCarDeletePopupComponent
    ],
    entryComponents: [
        SysShoppingCarComponent,
        SysShoppingCarUpdateComponent,
        SysShoppingCarDeleteDialogComponent,
        SysShoppingCarDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysShoppingCarModule {}
