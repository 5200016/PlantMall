/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysAdminComponent } from 'app/entities/sys-admin/sys-admin.component';
import { SysAdminService } from 'app/entities/sys-admin/sys-admin.service';
import { SysAdmin } from 'app/shared/model/sys-admin.model';

describe('Component Tests', () => {
    describe('SysAdmin Management Component', () => {
        let comp: SysAdminComponent;
        let fixture: ComponentFixture<SysAdminComponent>;
        let service: SysAdminService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysAdminComponent],
                providers: []
            })
                .overrideTemplate(SysAdminComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysAdminComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysAdminService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysAdmin(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysAdmins[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
