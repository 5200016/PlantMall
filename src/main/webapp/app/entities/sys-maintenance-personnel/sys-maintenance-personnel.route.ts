import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysMaintenancePersonnel } from 'app/shared/model/sys-maintenance-personnel.model';
import { SysMaintenancePersonnelService } from './sys-maintenance-personnel.service';
import { SysMaintenancePersonnelComponent } from './sys-maintenance-personnel.component';
import { SysMaintenancePersonnelDetailComponent } from './sys-maintenance-personnel-detail.component';
import { SysMaintenancePersonnelUpdateComponent } from './sys-maintenance-personnel-update.component';
import { SysMaintenancePersonnelDeletePopupComponent } from './sys-maintenance-personnel-delete-dialog.component';
import { ISysMaintenancePersonnel } from 'app/shared/model/sys-maintenance-personnel.model';

@Injectable({ providedIn: 'root' })
export class SysMaintenancePersonnelResolve implements Resolve<ISysMaintenancePersonnel> {
    constructor(private service: SysMaintenancePersonnelService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysMaintenancePersonnel> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysMaintenancePersonnel>) => response.ok),
                map((sysMaintenancePersonnel: HttpResponse<SysMaintenancePersonnel>) => sysMaintenancePersonnel.body)
            );
        }
        return of(new SysMaintenancePersonnel());
    }
}

export const sysMaintenancePersonnelRoute: Routes = [
    {
        path: 'sys-maintenance-personnel',
        component: SysMaintenancePersonnelComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMaintenancePersonnel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-maintenance-personnel/:id/view',
        component: SysMaintenancePersonnelDetailComponent,
        resolve: {
            sysMaintenancePersonnel: SysMaintenancePersonnelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMaintenancePersonnel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-maintenance-personnel/new',
        component: SysMaintenancePersonnelUpdateComponent,
        resolve: {
            sysMaintenancePersonnel: SysMaintenancePersonnelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMaintenancePersonnel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-maintenance-personnel/:id/edit',
        component: SysMaintenancePersonnelUpdateComponent,
        resolve: {
            sysMaintenancePersonnel: SysMaintenancePersonnelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMaintenancePersonnel.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysMaintenancePersonnelPopupRoute: Routes = [
    {
        path: 'sys-maintenance-personnel/:id/delete',
        component: SysMaintenancePersonnelDeletePopupComponent,
        resolve: {
            sysMaintenancePersonnel: SysMaintenancePersonnelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMaintenancePersonnel.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
