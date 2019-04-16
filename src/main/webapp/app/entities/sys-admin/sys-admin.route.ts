import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysAdmin } from 'app/shared/model/sys-admin.model';
import { SysAdminService } from './sys-admin.service';
import { SysAdminComponent } from './sys-admin.component';
import { SysAdminDetailComponent } from './sys-admin-detail.component';
import { SysAdminUpdateComponent } from './sys-admin-update.component';
import { SysAdminDeletePopupComponent } from './sys-admin-delete-dialog.component';
import { ISysAdmin } from 'app/shared/model/sys-admin.model';

@Injectable({ providedIn: 'root' })
export class SysAdminResolve implements Resolve<ISysAdmin> {
    constructor(private service: SysAdminService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysAdmin> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysAdmin>) => response.ok),
                map((sysAdmin: HttpResponse<SysAdmin>) => sysAdmin.body)
            );
        }
        return of(new SysAdmin());
    }
}

export const sysAdminRoute: Routes = [
    {
        path: 'sys-admin',
        component: SysAdminComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysAdmin.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-admin/:id/view',
        component: SysAdminDetailComponent,
        resolve: {
            sysAdmin: SysAdminResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysAdmin.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-admin/new',
        component: SysAdminUpdateComponent,
        resolve: {
            sysAdmin: SysAdminResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysAdmin.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-admin/:id/edit',
        component: SysAdminUpdateComponent,
        resolve: {
            sysAdmin: SysAdminResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysAdmin.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysAdminPopupRoute: Routes = [
    {
        path: 'sys-admin/:id/delete',
        component: SysAdminDeletePopupComponent,
        resolve: {
            sysAdmin: SysAdminResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysAdmin.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
