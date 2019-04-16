/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysAdminUpdateComponent } from 'app/entities/sys-admin/sys-admin-update.component';
import { SysAdminService } from 'app/entities/sys-admin/sys-admin.service';
import { SysAdmin } from 'app/shared/model/sys-admin.model';

describe('Component Tests', () => {
    describe('SysAdmin Management Update Component', () => {
        let comp: SysAdminUpdateComponent;
        let fixture: ComponentFixture<SysAdminUpdateComponent>;
        let service: SysAdminService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysAdminUpdateComponent]
            })
                .overrideTemplate(SysAdminUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysAdminUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysAdminService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysAdmin(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysAdmin = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysAdmin();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysAdmin = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
